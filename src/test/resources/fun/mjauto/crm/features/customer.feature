Feature: 管理员登录
  作为一个管理员我希望能够登录到我的账户以便能够操作客户信息

  Background:
    Given 管理员已经登录

  Scenario Outline: 获取客户信息:<page>-<size>-<isReverse>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 每页:"<size>"条,第"<page>"页
    And 按照"<rankKey>"排序,是否逆序:"<isReverse>"
    And 点击获取客户信息
    Then 返回该页数据,共"<res>"条
    Examples:
      | property | condition | value1 | value2 | size | page | rankKey | isReverse | res |
      | id       | Between   | 1      | 16     | 16   | 1    | id      | false     | 16  |
      | id       | Between   | 1      | 16     | 16   | 1    | id      | true      | 16  |
      | id       | Between   | 1      | 16     | 8    | 1    | id      | false     | 8   |
      | id       | Between   | 1      | 16     | 8    | 2    | id      | false     | 8   |

  Scenario Outline: 获取客户人数:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 点击获取客户人数
    Then 返回行数,共"<res>"条
    Examples:
      | property     | condition            | value1 | value2 | res |
      | id           | IsNull               |        |        | 0   |
      | id           | IsNotNull            |        |        | 34  |
      | id           | EqualTo              | 1      |        | 1   |
      | id           | LessThan             | 16     |        | 15  |
      | id           | LessThanOrEqualTo    | 16     |        | 16  |
      | id           | NotEqualTo           | 1      |        | 33  |
      | id           | GreaterThan          | 1      |        | 33  |
      | id           | GreaterThanOrEqualTo | 1      |        | 34  |
      | id           | In                   | 1,16   |        | 2   |
      | id           | NotIn                | 1,16   |        | 32  |
      | id           | Between              | 1      | 16     | 16  |
      | id           | NotBetween           | 1      | 16     | 18  |

      | customerName | IsNull               |        |        | 0   |
      | customerName | IsNotNull            |        |        | 34  |
      | customerName | EqualTo              | Mr.Sun |        | 1   |
      | customerName | LessThan             | Mr.Sun |        | 10  |
      | customerName | LessThanOrEqualTo    | Mr.Sun |        | 11  |
      | customerName | NotEqualTo           | Mr.Sun |        | 33  |
      | customerName | GreaterThan          | Mr.Sun |        | 23  |
      | customerName | GreaterThanOrEqualTo | Mr.Sun |        | 24  |
      | customerName | In                   | Mr.Li,Mr.Sun |  | 2   |
      | customerName | NotIn                | Mr.Li,Mr.Sun |  | 32  |
      | customerName | Between              | Mr.Li  | Mr.Sun | 4   |
      | customerName | NotBetween           | Mr.Li  | Mr.Sun | 30  |
      | customerName | Like                 | Mr%    |        | 32  |
      | customerName | NotLike              | Mr%    |        | 2   |

  Scenario Outline: 删除客户信息:<property>-<condition>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 点击删除客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | property        | condition            | value1 | value2 | res |
      | companyPosition | IsNull               |        |        | 2   |
      | businessLicence | IsNotNull            |        |        | 2   |
      | id              | EqualTo              | 21     |        | 1   |
      | id              | In                   | 22, 23 |        | 2   |
      | id              | Between              | 24     | 25     | 2   |
      | customerName    | Like                 | Md%    |        | 2   |

  Scenario Outline: 根据主键删除客户信息:<id>
    When 点击通过主键值:"<id>"删除客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | id  | res |
      | 28  | 1   |

  Scenario Outline: 插入一条数据:<arg1>
    When 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 点击插入数据
    Then 返回行数,共"<res>"条
    Examples:
      | arg1   | arg2  | arg3 | arg4    | arg5 | arg6 | arg7 | arg8 | arg9 | arg10   | res |
      | Mr.Zhu | 张三   | 123  | 法外狂徒 | 男    | 9528 |      | 1    | 1    | 插入数据 | 1   |

  Scenario Outline: 插入部分数据:<arg1>
    When 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 点击插入部分数据
    Then 返回行数,共"<res>"条
    Examples:
      | arg1   | arg2 | arg3 | arg4    | arg5 | arg6 | arg7 | arg8 | arg9 | arg10     | res |
      | Mr.Zhu | 张三  | 123  | 法外狂徒 | 男   | 9528 |      | 1    | 1    | 插入部分数据 | 1   |

  Scenario Outline: 根据主键查询客户信息:<id>
    When 点击通过主键值:"<id>"查询客户信息
    Then 返回一条数据,ID为"<res>"
    Examples:
      | id  | res |
      | 1   | 1   |

  Scenario Outline: 更新部分客户信息:<property>-<condition>-<arg1>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 更新部分客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | property | condition | value1 | value2 | arg1   | arg2  | arg3 | arg4    | arg5 | arg6 | arg7 | arg8 | arg9 | arg10         | res |
      | id       | In        | 29, 30 |        | Mr.Zhu | 张三   | 123  | 法外狂徒 | 男    | 9528 |      | 1    | 1    | 更新部分客户信息 | 2   |

  Scenario Outline: 更新客户信息:<property>-<condition>-<arg1>
    When 字段:"<property>",条件:"<condition>",值一:"<value1>",值二:"<value2>"
    And 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 更新客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | property | condition | value1 | value2 | arg1   | arg2 | arg3 | arg4  | arg5 | arg6 | arg7 | arg8 | arg9 | arg10     | res |
      | id       | In        | 31, 32 |        | Mr.Qin | 李四  | 456  | 超人强 | 女   | 9529 |      | 2    | 2    | 更新客户信息 | 2   |

  Scenario Outline: 根据主键值更新部分客户信息:<id>
    When 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 根据主键值"<id>"更新部分客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | id | arg1   | arg2 | arg3 | arg4    | arg5 | arg6 | arg7 | arg8 | arg9 | arg10                 | res |
      | 33 | Mr.Zhu | 张三  | 123  | 法外狂徒 | 男    | 9528 |      | 1    | 1    | 根据主键值更新部分客户信息 | 1   |

  Scenario Outline: 根据主键值更新客户信息:<id>
    When 插入数据:"<arg1>","<arg2>","<arg3>","<arg4>","<arg5>","<arg6>","<arg7>","<arg8>","<arg9>","<arg10>"
    And 根据主键值"<id>"更新客户信息
    Then 返回行数,共"<res>"条
    Examples:
      | id | arg1   | arg2 | arg3 | arg4  | arg5 | arg6 | arg7 | arg8 | arg9 | arg10             | res |
      | 34 | Mr.Qin | 李四  | 456  | 超人强 | 女   | 9529 |      | 2    | 2    | 根据主键值更新客户信息 | 1   |




































