# controle_de_estoque

Simulamos o funcionamento de uma loja com dez produtos e quatros funcionários, sendo dois vendedores e dois repositores de estoque. Nessa situação o processo de venda e reposição são processos dependentes.

Inicialmente, supomos que a loja esteja sem estoque e as mercadorias vão sendo repostas e vendidas de modo aleatório, ou seja, tanto a mercadoria quanto a quantidade da mesma é selecionada por meio de um sorteio pelo repositor ou vendedor, sendo que esses processos são independentes e a quantidade de produtos vendidos ou repostos pode ser de 1 a 5. No entanto, se alguém quiser comprar mais produtos que o disponível, a quantidade vendida é referente a quantidade disponível. Exemplo, se alguém deseja comprar quatro unidades do produto AD01, mas só existem 2 unidades em estoque; então o processo de venda é realizado com apenas as duas unidades. A mesma ideia é realizada no processo de reposição, ou seja, se repõe a quantidade de produtos sorteada ou um resultado menor desde que exista “espaço na prateleira”. Esses mecanismos de controle, bem como a condição de existência do produto em estoque para repor ou vender foram criadas para que o programa não se obrigue a esperar indefinidamente um processo que nunca poderá acontecer. Na prática isso é o mesmo que: desejar comprar um produto que não está mais disponível no mercado, assim, nunca será reposto ou querer repor um produto que ninguém tem interesse em comprar. No programa computacional isso seria o problema já apresentado como starvation. 
O processo de venda e reposição é executado 1.000.000 de vezes por cada funcionário. Assim, são realizadas 4.000.000 de interações.

Foram desenvolvidos seis programas em JAVA, 
O arquivo SingleSP.java é referente ao processo sequencial sem impressão dos resultados.
O arquivo SingleCP.java é referente ao processo sequencial com impressão dos resultados.
O arquivo MultiSPSS.java é referente ao processo multithread sem impressão dos resultados e sem o monitor de sincronização (syncronized).
O arquivo MultiCPSS.java é referente ao processo multithread com impressão dos resultados e sem o monitor de sincronização (syncronized).
O arquivo MultiSPCS.java é referente ao processo multithread sem impressão dos resultados e com o monitor de sincronização (syncronized).
O arquivo MultiCPCS.java é referente ao processo multithread com impressão dos resultados e com o monitor de sincronização (syncronized).
