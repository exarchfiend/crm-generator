Feature: 管理员登录
  作为一个管理员我希望能够登录到我的账户以便能够操作客户信息

  Background:
    Given 管理员已经登录

  Scenario Outline: 管理员查看所有客户信息
    When 符合这些"<conditions>"条件
    And 每页"<size>"条,第"<page>"页
    And 按照"<rankKey>"顺序"<isReverse>"排序
    And 点击查询
    Then 将该页数据返回给页面
    Examples:
      | conditions | size | page | rankKey | isReverse |
      | YES        | 10   | 2    | id      | true      |