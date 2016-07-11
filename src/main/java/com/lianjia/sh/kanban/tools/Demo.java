package com.lianjia.sh.kanban.tools;

import com.lianjia.sh.kanban.tools.code.CodeFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * @author ouyang
 * @since 2016-07-06 15:10
 */
public class Demo {

    private static final TreeMap<String, String> KANBAN_TABLE_MAP = new TreeMap<>();

    static {
        KANBAN_TABLE_MAP.put("case", "");
        KANBAN_TABLE_MAP.put("dict", "");
        KANBAN_TABLE_MAP.put("function", "");
        KANBAN_TABLE_MAP.put("meeting", "");
        KANBAN_TABLE_MAP.put("member", "");
        KANBAN_TABLE_MAP.put("relationCaseFunction", "");
        KANBAN_TABLE_MAP.put("relationCaseLabel", "");
        KANBAN_TABLE_MAP.put("relationCaseMeeting", "");
        KANBAN_TABLE_MAP.put("relationCaseMember", "");
        KANBAN_TABLE_MAP.put("relationMeetingMember", "");
        KANBAN_TABLE_MAP.put("relationTeamCase", "");
        KANBAN_TABLE_MAP.put("relationTeamMember", "");
        KANBAN_TABLE_MAP.put("team", "");
        KANBAN_TABLE_MAP.put("user", "");
    }

    /**
     * 测试 demo 用法 demo
     * @param args args
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @author ouyang
     * @since 2015-02-12 17:40
     */
    public static void main(String... args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        for (String tableName : KANBAN_TABLE_MAP.keySet()) {
            CodeFactory.generateJavaBean(DemoCodeFactoryConnection.class, tableName, "com.lianjia.sh.kanban.model", "ouyang");
        }
    }

}
