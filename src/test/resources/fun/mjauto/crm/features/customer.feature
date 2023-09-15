Feature: 管理员登录
  作为一个管理员我希望能够登录到我的账户以便能够操作客户信息

  Background:
    Given 管理员已经登录

  Scenario Outline: 管理员查看客户信息
    When 条件:"<condition>",字段:"<property>",值一:"<value1>",值二:"<value2>"
    And 每页:"<size>"条,第"<page>"页
    And 按照"<rankKey>"排序,是否逆序:"<isReverse>"
    And 点击查询
    Then 返回该页数据,共"<res>"条
    Examples:
      | condition | property | value1 | value2 | size | page | rankKey | isReverse | res |
      | id        | Between  | 1      | 1000   | 10   | 2    | id      | true      | 10  |
      | id        | Between  | 1      | 1000   | 10   | 3    | id      | true      | 10  |