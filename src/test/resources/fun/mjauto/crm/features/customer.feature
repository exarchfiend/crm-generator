Feature: 管理员登录
  作为一个管理员我希望能够登录到我的账户以便能够操作客户信息

  Background:
    Given 管理员已经登录

  Scenario Outline: 获取客户信息:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 每页:"<size>"条,第"<page>"页
    And 按照"<rankKey>"排序,是否逆序:"<isReverse>"
    And 点击获取客户信息
    Then 返回该页数据,共"<res>"条
    Examples:
      | property     | condition            | value1  | value2 | size | page | rankKey | isReverse | res |
      | id           | IsNull               |         |        | 10   | 1    | id      | false     | 0   |
      | id           | IsNotNull            |         |        | 10   | 1    | id      | false     | 10  |
      | id           | EqualTo              | 1       |        | 10   | 1    | id      | false     | 1   |
      | id           | LessThan             | 1000    |        | 10   | 1    | id      | false     | 10  |
      | id           | NotEqualTo           | 1       |        | 10   | 1    | id      | false     | 10  |
      | id           | GreaterThan          | 1       |        | 10   | 1    | id      | false     | 10  |
      | id           | GreaterThanOrEqualTo | 1       |        | 10   | 1    | id      | false     | 10  |
      | id           | LessThanOrEqualTo    | 1000    |        | 10   | 1    | id      | false     | 10  |
      | id           | In                   | 1, 10   |        | 10   | 1    | id      | false     | 2   |
      | id           | NotIn                | 1, 10   |        | 10   | 1    | id      | false     | 10  |
      | id           | Between              | 1       | 1000   | 10   | 1    | id      | false     | 10  |
      | id           | NotBetween           | 1       | 1000   | 10   | 1    | id      | false     | 0   |
      | customerName | Like                 | 王%     | 1000   | 10   | 1    | id      | false     | 10  |
      | customerName | NotLike              | 王%     | 1000   | 10   | 1    | id      | false     | 3   |

  Scenario Outline: 获取客户人数:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 点击获取客户人数
    Then 返回行数,共"<res>"条
    Examples:
      | property     | condition            | value1 | value2 | res |
      | id           | IsNull               |        |        | 0   |
      | id           | IsNotNull            |        |        | 31  |
      | id           | EqualTo              | 1      |        | 1   |
      | id           | LessThan             | 1000   |        | 31  |
      | id           | NotEqualTo           | 1      |        | 30  |
      | id           | GreaterThan          | 1      |        | 30  |
      | id           | GreaterThanOrEqualTo | 1      |        | 31  |
      | id           | LessThanOrEqualTo    | 1000   |        | 31  |
      | id           | In                   | 1, 10  |        | 2   |
      | id           | NotIn                | 1, 10  |        | 29  |
      | id           | Between              | 1      | 1000   | 31  |
      | id           | NotBetween           | 1      | 1000   | 0   |
      | customerName | Like                 | 王%     |       | 28  |
      | customerName | NotLike              | 王%     |       | 3   |

  Scenario Outline: 删除客户信息:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 点击删除客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | property        | condition            | value1 | value2 | res |
      | id              | IsNull               |        |        | 0   |
      | businessLicence | IsNotNull            |        |        | 1   |
      | id              | EqualTo              | 1      |        | 1   |
      | id              | LessThan             | 11     |        | 1   |
      | customerGender  | NotEqualTo           | 男     |        | 1   |
      | id              | GreaterThan          | 551    |        | 1   |
      | id              | GreaterThanOrEqualTo | 551    |        | 1   |
      | id              | LessThanOrEqualTo    | 515    |        | 1   |
      | id              | In                   | 1, 10  |        | 2   |
      | id              | Between              | 516    | 517    | 2   |
      | id              | NotBetween           | 1      | 549    | 1   |
      | customerName    | Like                 | 张%     |        | 1   |
      | customerName    | NotLike              | 王%     |        | 0   |
      | id              | NotIn                | 1, 10  |        | 20  |

  Scenario Outline: 根据删除客户信息:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 点击删除客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | property        | condition            | value1 | value2 | res |
      | id              | IsNull               |        |        | 0   |
      | businessLicence | IsNotNull            |        |        | 1   |
      | id              | EqualTo              | 1      |        | 1   |
      | id              | LessThan             | 11     |        | 1   |
      | customerGender  | NotEqualTo           | 男      |        | 1   |
      | id              | GreaterThan          | 551    |        | 1   |
      | id              | GreaterThanOrEqualTo | 551    |        | 1   |
      | id              | LessThanOrEqualTo    | 515    |        | 1   |
      | id              | In                   | 1, 10  |        | 2   |
      | id              | Between              | 516    | 517    | 2   |
      | id              | NotBetween           | 1      | 549    | 1   |
      | customerName    | Like                 | 张%     |        | 1   |
      | customerName    | NotLike              | 王%     |        | 0   |
      | id              | NotIn                | 1, 10  |        | 20  |













































