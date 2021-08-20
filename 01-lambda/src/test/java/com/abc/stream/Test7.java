package com.abc.stream;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Test7 {

    private List<Student> students;

    @BeforeEach
    public void before() {
        Student student0 = new Student("周零", "清华大学", "男", 20, 95.5);
        Student student1 = new Student("郑一", "清华大学", "女", 22, 98.5);
        Student student2 = new Student("吴二", "北京大学", "男", 21, 95.5);
        Student student3 = new Student("张三", "复旦大学", "男", 24, 90.5);
        Student student4 = new Student("李四", "清华大学", "女", 22, 92.5);
        Student student5 = new Student("王五", "北京大学", "男", 20, 93.5);
        Student student6 = new Student("赵六", "清华大学", "男", 23, 96.5);
        Student student7 = new Student("钱七", "复旦大学", "女", 22, 97.5);
        Student student8 = new Student("秦八", "复旦大学", "男", 21, 99.5);
        Student student9 = new Student("段九", "北京大学", "女", 20, 92.5);


        students = Arrays.asList(student0,student1,student2,student3,
                student4,student5,student6,student7,student8,student9);
    }

    // 获取所有学生姓名列表
    @Test
    public void test01() {
        List<String> names = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    // 获取所有参赛院校名单
    @Test
    public void test02() {
        Set<String> schools = students.stream()
                .map(Student::getSchool)
                .collect(Collectors.toSet());
        System.out.println(schools);
    }

    // 获取所有参赛院校名单
    @Test
    public void test03() {
        Set<String> schools = students.stream()
                .map(Student::getSchool)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(schools);
    }

    // 获取各个学校的学生(按照学校进行分组)
    @Test
    public void test04() {
        Map<String, List<Student>> schoolGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getSchool));

        // 使用工具类显示map中的key-value
        MapUtils.verbosePrint(System.out, "学校", schoolGroup);

        // 获取key为"清华大学"的value
        System.out.println(schoolGroup.get("清华大学"));

    }

    // 统计各个学校参赛选手人数
    @Test
    public void test05() {
        Map<String, Long> schoolCount = students.stream()
                .collect(Collectors.groupingBy(Student::getSchool, Collectors.counting()));

        System.out.println(schoolCount);

        // 获取key为"清华大学"的value
        System.out.println(schoolCount.get("清华大学"));

    }

    // 按照性别对所有参赛选手分组
    @Test
    public void test06() {
        Map<String, List<Student>> genderGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGender));

        // 使用工具类显示map中的key-value
        MapUtils.verbosePrint(System.out, "性别", genderGroup);

        // 获取所有男生
        System.out.println(genderGroup.get("男"));

    }

    // 按照性别对所有参赛选手分组
    @Test
    public void test07() {
        Map<Boolean, List<Student>> genderGroup = students.stream()
                .collect(Collectors.partitioningBy(s -> "男".equals(s.getGender())));

        // 使用工具类显示map中的key-value
        MapUtils.verbosePrint(System.out, "性别", genderGroup);

        // 获取所有男生
        System.out.println(genderGroup.get(true));

    }

    // 按照95为标准将所有参赛选手分组
    @Test
    public void test08() {
        Map<Boolean, List<Student>> genderGroup = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getScore() > 95));

        // 使用工具类显示map中的key-value
        MapUtils.verbosePrint(System.out, "95标准成绩", genderGroup);

        // 获取所有成绩大于95的学生
        System.out.println(genderGroup.get(true));

    }

    // 按照95为标准将所有参赛选手分组,上下两个分组的平均分各是多少
    @Test
    public void test09() {
        Map<Boolean, Double> scoreGroupAvg = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getScore() > 95, Collectors.averagingDouble(Student::getScore)));

        System.out.println(scoreGroupAvg);

        // 获取所有成绩大于95的所有学生平均分
        System.out.println(scoreGroupAvg.get(true));

    }

    // 获取成绩相关的统计数据
    @Test
    public void test10() {
        DoubleSummaryStatistics scoreSummary = students.stream()
                .collect(Collectors.summarizingDouble(Student::getScore));

        System.out.println(scoreSummary);
        // 输出成绩的数量
        System.out.println(scoreSummary.getCount());
        // 输出成绩中的最大值
        System.out.println(scoreSummary.getMax());

    }

    /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    @Test
    public void test11() {
        Map<String, Student> map = students.stream()
                .collect(Collectors.toMap(Student::getName, student -> student, (k1, k2) -> k1));

        System.out.println(map);

    }

}
