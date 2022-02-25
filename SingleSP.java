import java.util.Random;
import java.util.Vector;

public class SingleSP
{
    public static void main(String[] args) throws Exception
    {
    	long comeco = System.currentTimeMillis();
    	
        // Define um número máximo de produtos e de loops
        int numero_maximo_de_produtos = 10, loops = 1000000;
        
        // Declara um vetor com o número máximo de produto
        Vector <Produto> produtos = new Vector <Produto> (numero_maximo_de_produtos);
        
        // Adiciona produtos ao vetor
        produtos.add(new Produto(
            "AD01",            // Código do produto
            "Apple Mac Mini",  // Nome do modelo
            0,                 // Quantidade inicial em estoque
            32,                // Quantidade máxima em estoque
            13999.99           // Preço
        ));
        produtos.add(new Produto(
            "AD02",
            "Apple Mac Pro",
            0,
            8,
            699999.99
        ));
        produtos.add(new Produto(
            "AD03",
            "Apple iMac de 24 polegadas",
            0,
            24,
            33999.99
        ));
        produtos.add(new Produto(
            "AL01",
            "MacBook Pro de 16 polegadas",
            0,
            16,
            79999.99
        ));
        produtos.add(new Produto(
            "AL02",
            "MacBook Pro de 14 polegadas",
            0,
            32,
            159999.99
        ));
        produtos.add(new Produto(
            "DD01",
            "Dell Workstation Precision",
            0,
            8,
            49999.99
        ));
        produtos.add(new Produto(
            "DD02",
            "Dell Precision",
            0,
            24,
            9999.99
        ));
        produtos.add(new Produto(
            "DL01",
            "Dell Inspiron",
            0,
            32,
            5999.99
        ));
        produtos.add(new Produto(
            "DL02",
            "Dell XPS de 13 polegadas",
            0,
            16,
            12999.99
        ));
        produtos.add(new Produto(
            "DL03",
            "Dell XPS de 15 polegadas",
            0,
            12,
            15999.99
        ));
        
        // Cria dois repositores
        Repositor r1 = new Repositor(produtos);
        Repositor r2 = new Repositor(produtos);
        
        // Cria dois vendedores
        Vendedor  v1 = new Vendedor(produtos);
        Vendedor  v2 = new Vendedor(produtos);
        
        // Executa os repositores e os vendedores
        for (int i = 0; i < loops; i++)
        {
			r1.run();
			v1.run();
			r2.run();
			v2.run();
        }
        
        long fim = System.currentTimeMillis();
        
        System.out.println("    O programa finalizou em " + (fim - comeco) + "ms!");
    }
}

class Repositor implements Runnable
{
    Vector produtos;

    public Repositor(Vector _produtos)
    {
        this.produtos = _produtos;
    }
    
    public void run()
    {
        int numero_de_itens, posicao_do_produto, quantidade_para_repor;
        
        Random aleatorio = new Random();
        Produto produto;

        // Pega o número de itens no vetor produtos
        numero_de_itens = this.produtos.size();
        
        // Sorteia um item para repor
        posicao_do_produto = aleatorio.nextInt(numero_de_itens);
        produto = (Produto)this.produtos.get(posicao_do_produto);
        
        // Sorteia uma quantidade para repor
        quantidade_para_repor = aleatorio.nextInt(4) + 1;
        
        // Realiza a repozição
        produto.repor(quantidade_para_repor);
    }
}

class Vendedor implements Runnable
{
    Vector produtos;

    public Vendedor(Vector _produtos)
    {
        this.produtos = _produtos;
    }
    
    public void run()
    {
        int numero_de_itens, posicao_do_produto, quantidade_para_retirar;
        
        Random aleatorio = new Random();
        Produto produto;

        // Pega o número de itens no vetor produtos
        numero_de_itens = this.produtos.size();
        
        // Sortei um item para realizar a venda
        posicao_do_produto = aleatorio.nextInt(numero_de_itens);
        produto = (Produto)this.produtos.get(posicao_do_produto);
        
        // Sorteia uma quantidade para repor
        quantidade_para_retirar = aleatorio.nextInt(4) + 1;
        
        // Realiza a repozição
        produto.vender(quantidade_para_retirar);
    }
}

class Produto
{
    String codigo;
    String nome;
    volatile int estoque; 
    volatile int limite_do_estoque;
    volatile double preco;
    
    public Produto (String _codigo, String _nome, int _estoque, int _limite_do_estoque, double _preco)
    {
        this.codigo            = _codigo;
        this.nome              = _nome;
        this.estoque           = _estoque;
        this.limite_do_estoque = _limite_do_estoque;
        this.preco             = _preco;
    }
    
    public synchronized void repor(int quantidade_para_repor)
    {
    	// Se o estoque for menor que o limite, permite a reposição
    	if (this.estoque < this.limite_do_estoque)
    	{
    		// Se a quantidade para repor extrapola o limite, repõe
    		// somente a quantidade disponível para reposição
    		if (quantidade_para_repor + this.estoque > this.limite_do_estoque)
    		{
    			quantidade_para_repor = this.limite_do_estoque - this.estoque;
    		}
    		
    		// Realiza o incremento do estoque
            this.estoque = this.estoque + quantidade_para_repor;
    	}
    }
    
    public void vender(int quantidade_para_retirar)
    {
    	// Se o estoque for maior que zero, permite a venda
    	if (this.estoque > 0)
    	{
    		// Se o estoque for insuficiente, vende a quantidade disponível
    		if (quantidade_para_retirar > this.estoque)
    		{
    			quantidade_para_retirar = this.estoque;
    		}
    		
            // Realiza o decremento do estoque
            this.estoque = this.estoque - quantidade_para_retirar;
    	}
    }
}
