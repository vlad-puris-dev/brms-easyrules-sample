package com.vvp.easyrulessample.model;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "rule_1", description = "Rule 1", priority = 1)
public class Rule1 {
    /**
     * Set rule condition.
     * @param account account
     * @return rule condition result
    */
    @Condition
    public boolean setCondition(@Fact("account") final Account account) {
        return ((account.getAccountType().equals("debit")) && (account.getAccountOrigin().equals("domestic")));
    }

    /**
     * Set aggregation details action.
     * @param account account
     */
    @Action
    public void setAction(@Fact("account") final Account account) {
        account.setPayInterestFlag("N");
        account.setTraceabilityDescription("Rule 1 applied");
    }
}
