# LexIDE
Projeto da disciplina de compiladores.

Tem como objetivo a criação de uma linguagem de programação simples, apenas para aprendizagem. Inclui uma IDE.

--

###Data types
| Data | Example |
| ---- | ------- |
| int | 16 |
| string | "Hello world!" |
| char | 'c' |
| float | 10.23 |
| vectors | int vet[] |

###Control structures
| Conditional clauses | Example |
| ------------------- | ------- |
| if | if (...) {...} |
| if else | if... else {...} |

| Loops | Example |
| ----- | ------- |
| while | while (...) {...} |
| do while | do {...} while (...); |
| for | fore (...) {...} |

###Operators

| Arithmetic | Example |
| ---------- | ------- |
| + | |
| - | |
| * | |
| / | |
| % | |

| Logical | Example |
| ------- | ------- |
| > | |
| < | |
| >= | |
| <= | |
| = | |
| == | |
| != | |
| || | |
| && | |
| \| | |
| ^ | |
| & | |
| ~ | |
| >> | |
| << | |

###Semantic actions
| what | id |
| ---- | ------- |
| name | #1 |
| type | #2 |
| inicialized | #3 |
| used | #4 |
| scope | #5 |
| param | #6 |
| position | #7 |
| vector | #8 |
| matrix | #9 |
| ref | #10 |
| func | #11 |
| final line | #12 |
| final scope | #13 |
| final code | #14 |
| assignment | #15 |
| value | #16 |
| read | #17 |
| write | #18 |

**final line:** detecta quando as informações para compor uma tupla da tabela já são o suficiente, por exemplo ";".

**final scope:** detecta o final de um escopo.
