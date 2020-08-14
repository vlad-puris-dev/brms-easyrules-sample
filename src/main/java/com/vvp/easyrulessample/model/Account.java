package com.vvp.easyrulessample.model;

import com.google.gson.GsonBuilder;

public class Account {
    /**
     * Account identifier.
     */
    private String accountId;
    /**
     * Account type.
     */
    private String accountType;
    /**
     * Account open date.
     */
    private String accountOpenDate;
    /**
     * Account close date.
     */
    private String accountCloseDate;
    /**
     * Account origin.
     */
    private String accountOrigin;
    /**
     * Account pay interest flag.
     */
    private String payInterestFlag;
    /**
     * Traceability description.
     */
    private String traceabilityDescription;

    /**
     * Constructor with no arguments.
     */
    public Account() {
    }

    /**
     * Constructor with arguments.
     * @param accountId account identifier
     * @param accountType account type
     * @param accountOpenDate account open date
     * @param accountCloseDate account close date
     * @param accountOrigin account origin
     */
    public Account(final String accountId, final String accountType, final String accountOpenDate,
            final String accountCloseDate, final String accountOrigin) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountOpenDate = accountOpenDate;
        this.accountCloseDate = accountCloseDate;
        this.accountOrigin = accountOrigin;
    }

    /**
     * Get account identifier.
     * @return account identifier
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Set account identifier.
     * @param accountId account identifier
     */
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    /**
     * Get account type.
     * @return account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Set account type.
     * @param accountType account type
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Get account open date.
     * @return account open date
     */
    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    /**
     * Set account open date.
     * @param accountOpenDate account open date
     */
    public void setAccountOpenDate(final String accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    /**
     * Get account close date.
     * @return account close date
     */
    public String getAccountCloseDate() {
        return accountCloseDate;
    }

    /**
     * Set account close date.
     * @param accountCloseDate account close date
     */
    public void setAccountCloseDate(final String accountCloseDate) {
        this.accountCloseDate = accountCloseDate;
    }

    /**
     * Get account origin.
     * @return account origin
     */
    public String getAccountOrigin() {
        return accountOrigin;
    }

    /**
     * Set account origin.
     * @param accountOrigin account origin
     */
    public void setAccountOrigin(final String accountOrigin) {
        this.accountOrigin = accountOrigin;
    }

    /**
     * Get pay interest flag.
     * @return pay interest flag
     */
    public String getPayInterestFlag() {
        return payInterestFlag;
    }

    /**
     * Set pay interest flag.
     * @param payInterestFlag pay interest flag
     */
    public void setPayInterestFlag(final String payInterestFlag) {
        this.payInterestFlag = payInterestFlag;
    }

    /**
     * Get traceability description.
     * @return traceability description
     */
    public String getTraceabilityDescription() {
        return traceabilityDescription;
    }

    /**
     * Set traceability description.
     * @param traceabilityDescription traceability description
     */
    public void setTraceabilityDescription(final String traceabilityDescription) {
        this.traceabilityDescription = traceabilityDescription;
    }

    /**
     * Clean aggregation values.
     */
    public void clean() {
        payInterestFlag = null;
        traceabilityDescription = null;
    }

    /**
     * Get string representation of object in json format.
     * @return string representation of object in json format
     */
    @Override
    public String toString() {
        return new GsonBuilder().serializeNulls().create().toJson(this).toString();
    }

    /**
     * Check if objects are equal.
     * @param obj object
     * @return object equal status
     */
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     * Get hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
