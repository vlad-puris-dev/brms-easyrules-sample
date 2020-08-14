package com.vvp.easyrulessample;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import com.vvp.easyrulessample.model.Account;
import com.vvp.easyrulessample.model.Rule1;
import com.vvp.easyrulessample.model.Rule2;
import com.vvp.easyrulessample.model.Rule3;

public class EasyRulesSampleApplication {
    /**
     * Main method that run the application.
     * @param args list of arguments to be used
     */
    public static void main(final String[] args) throws Exception {
        Account account1 = new Account("1", "debit", "01/01/2020", "31/12/2020", "domestic");
        Facts facts1 = new Facts();
        facts1.put("account", account1);

        Account account2 = new Account("2", "credit", "01/01/2020", "31/12/2020", "domestic");
        Facts facts2 = new Facts();
        facts2.put("account", account2);

        Account account3 = new Account("3", "credit", "01/01/2020", "31/12/2020", "foreign");
        Facts facts3 = new Facts();
        facts3.put("account", account3);

        RulesEngine ruleEngine = new DefaultRulesEngine();
        Rules rules = new Rules();

        rules = createManualClassRules();
        ruleEngine.fire(rules, facts1);
        ruleEngine.fire(rules, facts2);
        ruleEngine.fire(rules, facts3);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("------------Aggregation results using manually created Class rules----------");
        printAccounts(account1, account2, account3);

        rules.clear();
        clean(account1, account2, account3);

        rules = createManualMVELRules();
        ruleEngine.fire(rules, facts1);
        ruleEngine.fire(rules, facts2);
        ruleEngine.fire(rules, facts3);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("------------Aggregation results using manually created MVEL rules----------");
        printAccounts(account1, account2, account3);

        rules.clear();
        clean(account1, account2, account3);

        rules = loadRulesFromJson();
        ruleEngine.fire(rules, facts1);
        ruleEngine.fire(rules, facts2);
        ruleEngine.fire(rules, facts3);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("------------Aggregation results using rules from Json file----------");
        printAccounts(account1, account2, account3);

        rules.clear();
        clean(account1, account2, account3);

        rules = loadRulesFromYml();
        ruleEngine.fire(rules, facts1);
        ruleEngine.fire(rules, facts2);
        ruleEngine.fire(rules, facts3);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("------------Aggregation results using rules from Yml file----------");
        printAccounts(account1, account2, account3);
    }

    /**
     * Create manual rules.
     * @return aggregation rules
     */
    private static Rules createManualClassRules() {
        Rules rules = new Rules();
        rules.register(new Rule1());
        rules.register(new Rule2());
        rules.register(new Rule3());
        return rules;
    }

    /**
     * Create manual MVEL rules.
     * @return aggregation rules
     */
    private static Rules createManualMVELRules() {
        Rules rules = new Rules();
        Rule rule1 = new MVELRule()
                .name("rule_1")
                .description("Rule 1")
                .priority(1)
                .when("((account.getAccountType().equals(\"debit\"))"
                        + " && (account.getAccountOrigin().equals(\"domestic\")))")
                .then("account.setPayInterestFlag(\"N\"); account.setTraceabilityDescription(\"Rule 1 applied\");");
        Rule rule2 = new MVELRule()
                .name("rule_2")
                .description("Rule 2")
                .priority(1)
                .when("((account.getAccountType().equals(\"credit\"))"
                        + " && (account.getAccountOrigin().equals(\"domestic\")))")
                .then("account.setPayInterestFlag(\"Y\"); account.setTraceabilityDescription(\"Rule 2 applied\");");
        Rule rule3 = new MVELRule()
                .name("rule_3")
                .description("Rule 3")
                .priority(1)
                .when("((account.getAccountType().equals(\"credit\"))"
                        + " && (account.getAccountOrigin().equals(\"foreign\")))")
                .then("account.setPayInterestFlag(\"Y\"); account.setTraceabilityDescription(\"Rule 3 applied\");");
        rules.register(rule1);
        rules.register(rule2);
        rules.register(rule3);
        return rules;
    }

    /**
     * Load rules from JSON file.
     * @return aggregation rules
     * @throws Exception exception thrown during rules load
     */
    private static Rules loadRulesFromJson() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
        return ruleFactory.createRules(new InputStreamReader(
                new FileInputStream("src/main/resources/rules/rules.json"), StandardCharsets.UTF_8));
    }

    /**
     * Load rules from YML file.
     * @return aggregation rules
     * @throws Exception exception thrown during rules load
     */
    private static Rules loadRulesFromYml() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        return ruleFactory.createRules(new InputStreamReader(
                new FileInputStream("src/main/resources/rules/rules.yml"), StandardCharsets.UTF_8));
    }

    /**
     * Print account details.
     * @param account1 account
     * @param account2 account
     * @param account3 account
     */
    private static void printAccounts(final Account account1, final Account account2, final Account account3) {
        System.out.println("Account1: " + account1.toString() + "   Expected = N");
        System.out.println("Account2: " + account2.toString() + "   Expected = Y");
        System.out.println("Account3: " + account3.toString() + "   Expected = Y");
    }

    /**
     * Clean accounts aggregation details.
     * @param account1 account
     * @param account2 account
     * @param account3 account
     */
    private static void clean(final Account account1, final Account account2, final Account account3) {
        account1.clean();
        account2.clean();
        account3.clean();
    }
}
