# ChiSquareTest

## 问题 / 解题
分析一个样本集的两个属性（这两个属性只有两个值域）的关联性

例如：
有，原始数据表格：
| 序号 | 与 A 病的患者在同一病房？ | 感染？ |
| --- | --- | --- |
| 1 | Yes | Yes |
| 2 | No | Yes |
| 3 | Yes | No |
| 4 | No | No |
| ... | ... | ... |

统计之后将得到统计表格：
| 感染 \ 同病房 | Yes | No |
| --- | --- | --- |
| Yes | 3 | 1 |
| No | 160 | 452 |

数值 [3, 1, 160, 452] 作为卡方校验的输入，最终输出：卡方值、P值、OR值
## 文件组成
```
- ChiSquareTest
  - ChiSquareTest_2_2.java            # 主函数文件，计算自由度为 1 的 [2×2] 的统计表的卡方值、P 值、OR 值，入参是4个统计值
  - ChiSquareDistribution.java        # 独立使用，计算卡方概率分布 f(x)，只能用积分的方式来求，生成卡方分布表（CSV 文件）
  - chisquare-distribution-table.csv  # 卡方分布表，给主函数查询用，精度为小数点后两位，x 的边界为 [0, 11]
```
## 使用
前提：要有 `chisquare-distribution-table.csv` 文件，如果没有，启动 `ChiSquareDistribution.java` 程序来生成

1 编译：`$ javac ChiSquareTest_2_2.java`

2 启动：`$ java ChiSquareTest_2_2 {args_0} {args_1} {args_2} {args_3}` 四个入参就是 2*2 统计表里的逐格的数据，最后在控制台打印结果。
