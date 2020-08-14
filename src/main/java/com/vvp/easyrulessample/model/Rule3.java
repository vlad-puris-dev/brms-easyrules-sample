package com.vvp.easyrulessample.model;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "rule_3", description = "Rule 3", priority = 1)
public class Rule3 {
    /**
     * Set rule condition.
     * @param account account
     * @return rule condition result
    */
    @Condition
    public boolean setCondition(@Fact("account") final Account account) {
        return ((account.getAccountType().equals("credit")) && (account.getAccountOrigin().equals("foreign")));
    }

    /**
     * Set aggregation details action.
     * @param account account
     */
    @Action
    public void setAction(@Fact("account") final Account account) {
        account.setPayInterestFlag("Y");
        account.setTraceabilityDescription("Rule 3 applied");
    }
}
