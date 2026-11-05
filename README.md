# taxcalculator
TaxCalculator - um kata para um exercício de Clean Code

1ª Alteração - apaguei uma variável que estava declarada duas vezes nos campos static

2ª Alteração - mudei o nome de advanceTaxPaidadvanceTax para advanceTaxPaid (na declaração e no calculateAdvanceTax()) porque o código usava advanceTaxPaid e essa variável não existia

3ª Alteração - mudei soc_sick_secur para soc_sick_security no calculateIncome(), a variável tinha o nome errado

4ª Alteração - na parte do C o income era declarado outra vez e usado para se calcular a si próprio, mudei para d_income como na parte do E

5ª Alteração - na parte do C havia uma variável advanceTax que não servia para nada, troquei por taxPaid porque a linha seguinte usa taxPaid e só estava declarada na parte do E

6ª Alteração - o d_income (rendimento sem as contribuições sociais) era calculado mas nunca usado; agora é a base para o seguro de saúde, para o rendimento tributável e para as despesas dedutíveis do C (20%)

7ª Alteração - separei as contas dos outputs: o TaxCalculator só faz as contas e devolve um TaxResult, o TaxPrinter só imprime (Do one thing)

8ª Alteração - as partes do E e do C tinham as mesmas contas copiadas, agora há só um calculate() (Needless repetition)

9ª Alteração - troquei os números mágicos (9.76, 1.5, 2.45, 9, 7.75, 18, 20, 111.25, 46.33) por constantes com nome (Replace magic numbers with named constants)

10ª Alteração - tirei as variáveis static globais que eram alteradas durante as contas, a parte do C mudava o taxFreeIncome e o taxDeductibleExpenses (Have no side effects)

11ª Alteração - mudei para nomes descritivos: d_income -> incomeBasis, soc_health1 -> healthTax, soc_health2 -> healthTaxDeductible, taxPaid -> reducedTax, df00 -> twoDecimals, df -> noDecimals, calculateIncome -> calculateSocialSecurity, calculateOtherTaxes -> calculateHealthTax (Choose descriptive and unambiguous names, Avoid encodings)

12ª Alteração - cada classe no seu ficheiro: Main (input), TaxCalculator (contas), TaxResult (valores), TaxPrinter (outputs), ContractType, UnknownContractTypeException

13ª Alteração - o char 'E'/'C' passou a ser o enum ContractType, cada tipo de contrato sabe as suas despesas dedutíveis e o seu rendimento isento, por isso o calculate() já não precisa de if/else (Don't use flag arguments, Prefer polymorphism to if/else)

14ª Alteração - o try-catch ficou sozinho no main() e o resto foi para o run(); um tipo de contrato desconhecido agora lança UnknownContractTypeException (o try-catch é uma tarefa separada, exceções em vez de valores especiais)

15ª Alteração - dividi o calculate() em métodos pequenos: calculateSocialSecurity, calculateHealthTax, calculateAdvanceTax, calculateNetIncome, pela ordem em que são chamados (Small, Place functions in the downward direction)

16ª Alteração - o TaxCalculator e o TaxPrinter usam métodos de instância em vez de static (Prefer non-static methods to static methods)

O output é igual ao que dava depois da 6ª alteração, testado com vários rendimentos para E e C, um tipo de contrato desconhecido e um rendimento inválido.

Para correr: Main.java


## Possível alteração futura: trocar double por BigDecimal

Esta alteração ainda não foi feita porque muda alguns outputs, e até aqui o objetivo era manter o output igual.

Problema: o double guarda os números em binário e alguns decimais não têm representação exata (tal como 1/3 não tem em decimal, fica 0,3333...). Por isso as contas podem perder ou ganhar uma fração muito pequena e os erros vão-se acumulando nas várias percentagens e subtrações.

Isto já se vê no output atual:
- com 1000 e contrato C aparece "income to be taxed = 690.3199999999999" em vez de 690.32
- com 1234.56 e contrato E aparece "income 954.0518239999999" em vez de 954.051824
- os valores incomeBasis, taxedIncome, advanceTax e taxDeductibleExpenses são impressos diretamente sem DecimalFormat, por isso estes erros aparecem no ecrã

Há também um problema no arredondamento: o DecimalFormat usa por defeito RoundingMode.HALF_EVEN, que arredonda o .5 para o número par (4202.5 -> 4202, 4203.5 -> 4204). Em contas de impostos o normal é HALF_UP (o .5 arredonda sempre para cima).

Solução:
- usar BigDecimal em vez de double em todos os valores de dinheiro (TaxResult, TaxCalculator e ContractType)
- criar os valores a partir de String (new BigDecimal("9.76")) e não a partir de double, senão o erro do double entra logo no BigDecimal
- fazer o arredondamento com setScale(0, RoundingMode.HALF_UP) em vez do Double.parseDouble(df.format(...))
- imprimir todos os valores com o mesmo formato, para o output ficar consistente

Consequência: alguns valores do output mudam ligeiramente (por exemplo 690.3199999999999 passa a 690.32 e alguns arredondamentos de .5 passam a ir para cima), por isso deve ser uma alteração separada e testada à parte.


usar fixed precisition, em flloating point não ha uma fixed position

redesenhar a arquitetura para caso haja novos contratos, para caso haja alterações num só contrato, como criar uma super classe para o contrato e depois classes filhos para os diferentes contratos. Os contrartos são construidos de blocos especificos, os blocos são os mesmos mas as infos diferentes, criar blocos e usar sepradamente