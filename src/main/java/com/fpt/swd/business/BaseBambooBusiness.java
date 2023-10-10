package com.fpt.swd.business;

import com.google.gson.Gson;
import com.hainam.bamboo.bean.BO.CategoryDataBO;
import com.hainam.bamboo.bean.BO.FieldType;
import com.hainam.bamboo.utils.DataUtil;
import com.hainam.bamboo.utils.FileUploadUtils;
import org.apache.poi.ss.usermodel.*;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

@Component
public abstract class BaseBambooBusiness<T, Q> {
    private static final Logger log = LoggerFactory.getLogger(BaseBambooBusiness.class);
    protected Gson gson = new Gson();
    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    protected FileUploadUtils fileUploadService;
    @Autowired
    protected EntityManager entityManager;
    @Value("${config.event.data-exporter}")
    private String topicExportData;
    protected int CODE_LENGTH = 10;
    private Class<T> clazzT;
    private Class<Q> clazzQ;

    public abstract String getType();

    public abstract String processGenerateReport(String object);

    public void BaseCategoryBusiness(Class<T> clazzT, Class<Q> clazzQ) {
        this.clazzT = clazzT;
        this.clazzQ = clazzQ;
    }

    protected void sendExportDataRequest(String username, Object requestParam, String clientCode) {
        CategoryDataBO reportData = CategoryDataBO.builder().username(username).categoryType(this.getType()).data(requestParam).clientCode(clientCode).build();
        this.kafkaTemplate.send(this.topicExportData, this.gson.toJson(reportData));
    }

    protected String uploadReport(String tempName, Map<String, Object> mData) {
        try {
            InputStream isTemplate = (new ClassPathResource(tempName)).getInputStream();

            String var13;
            try {
                ByteArrayOutputStream osReportOutput = new ByteArrayOutputStream();

                try {
                    Context context = new Context();
                    Iterator var6 = mData.entrySet().iterator();

                    while(true) {
                        if (!var6.hasNext()) {
                            JxlsHelper.getInstance().processTemplate(isTemplate, osReportOutput, context);
                            var13 = this.fileUploadService.uploadFile(osReportOutput.toByteArray(), "subuu-dev", "reports", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                            break;
                        }

                        Map.Entry<String, Object> entry = (Map.Entry)var6.next();
                        context.putVar((String)entry.getKey(), entry.getValue());
                    }
                } catch (Throwable var10) {
                    try {
                        osReportOutput.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }

                    throw var10;
                }

                osReportOutput.close();
            } catch (Throwable var11) {
                if (isTemplate != null) {
                    try {
                        isTemplate.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (isTemplate != null) {
                isTemplate.close();
            }

            return var13;
        } catch (IOException var12) {
            throw new RuntimeException(var12);
        }
    }

    public List<T> previewData(InputStream inputStream, List<Q> lstExtendAttr, int fromRow, int fromCol) {
        List<T> lstResult = new ArrayList();

        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Field[] fields = this.clazzT.getDeclaredFields();
            Map<Integer, Field> mapField = this.sortListField(fields, false);
            Field extendFieldValue = null;
            if (!DataUtil.isNullObject(lstExtendAttr)) {
                Field[] fieldsQ = this.clazzQ.getDeclaredFields();

                for(int i = 0; i < fieldsQ.length; ++i) {
                    Field field = fieldsQ[i];
                    field.setAccessible(true);
                    FieldType fieldType = (FieldType)field.getDeclaredAnnotation(FieldType.class);
                    if (!DataUtil.isNullObject(fieldType) && fieldType.importIndex() != -1) {
                        extendFieldValue = field;
                    }
                }
            }

            int numColNeedRead = 0;
            int i;
            if (fromRow > 0) {
                Row row = sheet.getRow(0);
                if (row.getFirstCellNum() < 0) {
                    return new ArrayList();
                }

                for(i = 0; i < row.getLastCellNum(); ++i) {
                    Cell cell = row.getCell(i);
                    Object cellValue = this.getCellValue(cell);
                    if (!DataUtil.isNullObject(cellValue)) {
                        ++numColNeedRead;
                    }
                }
            }

            Field extendField = null;

            for(i = fromRow; i <= sheet.getLastRowNum(); ++i) {
                Row row = sheet.getRow(i);
                if (row.getFirstCellNum() >= 0) {
                    int extendIndex = 0;
                    int fieldIndex = 0;
                    boolean hasData = false;
                    List<Q> lstExtendAttrNew = new ArrayList();
                    Iterator var19 = lstExtendAttr.iterator();

                    while(var19.hasNext()) {
                        Q q = (Q) var19.next();
                        Q q1 = this.clazzQ.newInstance();
                        BeanUtils.copyProperties(q, q1);
                        lstExtendAttrNew.add(q1);
                    }

                    T objectT = this.clazzT.newInstance();

                    for(int y = fromCol; y < row.getLastCellNum(); ++y) {
                        Cell cell = row.getCell(i);
                        Object cellValue = this.getCellValue(cell);
                        if (!DataUtil.isNullObject(cellValue) && y < numColNeedRead) {
                            hasData = true;
                        }

                        Field field = (Field)mapField.get(fieldIndex);
                        if (field != null) {
                            field.setAccessible(true);
                            FieldType fieldType = (FieldType)field.getDeclaredAnnotation(FieldType.class);
                            if (DataUtil.isNullObject(fieldType) || fieldType.importIndex() == -1) {
                                continue;
                            }

                            if (!fieldType.importExtendField()) {
                                field.set(objectT, this.parseValue(field, cellValue));
                                ++fieldIndex;
                                continue;
                            }

                            extendField = field;
                        }

                        if (!DataUtil.isNullObject(lstExtendAttrNew) && extendFieldValue != null && extendIndex < lstExtendAttrNew.size()) {
                            Q objectQ = lstExtendAttrNew.get(extendIndex);
                            extendFieldValue.set(objectQ, this.parseValue(extendFieldValue, cellValue));
                            ++extendIndex;
                            if (extendIndex == lstExtendAttrNew.size()) {
                                ++fieldIndex;
                            }
                        }
                    }

                    if (extendFieldValue != null && !DataUtil.isNullObject(lstExtendAttrNew)) {
                        extendField.set(objectT, lstExtendAttrNew);
                    }

                    if (hasData) {
                        lstResult.add(objectT);
                    }
                }
            }

            workbook.close();
        } catch (Exception var25) {
            log.error(var25.getMessage(), var25);
        }

        return lstResult;
    }

    private Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        } else {
            CellType cellType = cell.getCellTypeEnum();
            Object cellValue = null;
            switch (cellType) {
                case BOOLEAN:
                    cellValue = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    Workbook workbook = cell.getSheet().getWorkbook();
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    cellValue = evaluator.evaluate(cell).getNumberValue();
                    break;
                case NUMERIC:
                    cellValue = cell.getNumericCellValue();
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                case _NONE:
                case BLANK:
                case ERROR:
            }

            return cellValue;
        }
    }

    private Object parseValue(Field field, Object input) {
        if (DataUtil.isNullObject(input)) {
            return null;
        } else {
            Class<?> fieldTypeClass = field.getType();
            if (fieldTypeClass == Long.class) {
                return Double.valueOf(input.toString()).longValue();
            } else if (fieldTypeClass == Integer.class) {
                return Double.valueOf(input.toString()).intValue();
            } else if (fieldTypeClass == Double.class) {
                return Double.valueOf(input.toString());
            } else {
                return fieldTypeClass == Boolean.class ? Boolean.valueOf(input.toString()) : input.toString();
            }
        }
    }

    private Map<Integer, Field> sortListField(Field[] fields, boolean isExport) {
        Map<Integer, Field> map = new TreeMap();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            field.setAccessible(true);
            FieldType fieldType = (FieldType)field.getDeclaredAnnotation(FieldType.class);
            if (!DataUtil.isNullObject(fieldType)) {
                if (isExport) {
                    if (fieldType.exportIndex() != -1) {
                        map.put(fieldType.exportIndex(), field);
                    }
                } else if (fieldType.importIndex() != -1) {
                    map.put(fieldType.importIndex(), field);
                }
            }
        }

        return new TreeMap(map);
    }

    protected Long getNextSequenceValue(String sequenceName) {
        String query = "SELECT NEXTVAL(" + sequenceName + ")";
        String data = this.entityManager.createNativeQuery(query).getSingleResult().toString();
        return Long.parseLong(data);
    }

    protected String generateCode(long sequenceNumber) {
        return String.format("%0" + this.CODE_LENGTH + "d", sequenceNumber);
    }

    protected String generateCode(String prefix, long sequenceNumber) {
        String var10000 = DataUtil.safeToString(prefix);
        return var10000 + String.format("%0" + this.CODE_LENGTH + "d", sequenceNumber);
    }
}
