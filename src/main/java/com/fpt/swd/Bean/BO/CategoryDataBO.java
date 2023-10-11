package com.fpt.swd.Bean.BO;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;

public class CategoryDataBO implements Serializable {
    private String username;
    private String categoryType;
    private String clientCode;
    private Object data;

    public static CategoryDataBOBuilder builder() {
        return new CategoryDataBOBuilder();
    }

    public CategoryDataBO() {
    }

    public CategoryDataBO(final String username, final String categoryType, final String clientCode, final Object data) {
        this.username = username;
        this.categoryType = categoryType;
        this.clientCode = clientCode;
        this.data = data;
    }

    public String getUsername() {
        return this.username;
    }

    public String getCategoryType() {
        return this.categoryType;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setCategoryType(final String categoryType) {
        this.categoryType = categoryType;
    }

    public void setClientCode(final String clientCode) {
        this.clientCode = clientCode;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CategoryDataBO)) {
            return false;
        } else {
            CategoryDataBO other = (CategoryDataBO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label59;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label59;
                    }

                    return false;
                }

                Object this$categoryType = this.getCategoryType();
                Object other$categoryType = other.getCategoryType();
                if (this$categoryType == null) {
                    if (other$categoryType != null) {
                        return false;
                    }
                } else if (!this$categoryType.equals(other$categoryType)) {
                    return false;
                }

                Object this$clientCode = this.getClientCode();
                Object other$clientCode = other.getClientCode();
                if (this$clientCode == null) {
                    if (other$clientCode != null) {
                        return false;
                    }
                } else if (!this$clientCode.equals(other$clientCode)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CategoryDataBO;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $categoryType = this.getCategoryType();
        result = result * 59 + ($categoryType == null ? 43 : $categoryType.hashCode());
        Object $clientCode = this.getClientCode();
        result = result * 59 + ($clientCode == null ? 43 : $clientCode.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getUsername();
        return "CategoryDataBO(username=" + var10000 + ", categoryType=" + this.getCategoryType() + ", clientCode=" + this.getClientCode() + ", data=" + this.getData() + ")";
    }

    public static class CategoryDataBOBuilder {
        private String username;
        private String categoryType;
        private String clientCode;
        private Object data;

        CategoryDataBOBuilder() {
        }

        public CategoryDataBOBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public CategoryDataBOBuilder categoryType(final String categoryType) {
            this.categoryType = categoryType;
            return this;
        }

        public CategoryDataBOBuilder clientCode(final String clientCode) {
            this.clientCode = clientCode;
            return this;
        }

        public CategoryDataBOBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public CategoryDataBO build() {
            return new CategoryDataBO(this.username, this.categoryType, this.clientCode, this.data);
        }

        public String toString() {
            return "CategoryDataBO.CategoryDataBOBuilder(username=" + this.username + ", categoryType=" + this.categoryType + ", clientCode=" + this.clientCode + ", data=" + this.data + ")";
        }
    }
}
