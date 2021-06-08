/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.util;

import br.ifsul.model.Categoria;
import br.ifsul.model.Cidade;
import br.ifsul.model.Compra;
import br.ifsul.model.CompraID;
import br.ifsul.model.CompraItem;
import br.ifsul.model.Endereco;
import br.ifsul.model.Estado;
import br.ifsul.model.Foto;
import br.ifsul.model.Marca;
import br.ifsul.model.Pais;
import br.ifsul.model.Parcela;
import br.ifsul.model.Permissao;
import br.ifsul.model.PessoaFisica;
import br.ifsul.model.PessoaJuridica;
import br.ifsul.model.Produto;
import br.ifsul.model.TipoEndereco;
import br.ifsul.model.Venda;
import br.ifsul.model.VendaItens;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author JSF
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IFSULPUWEB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        /*
        -- Pais 
        Pais p = new Pais();
        p.setId(null);
        p.setNome("Uruguai");
        p.setIso("URU");
        
        -- Estado
        Estado e = new Estado();
        e.setNome("São Paulo");
        e.setUf("SP");
        e.setPais(em.find(Pais.class, 1));
        
        -- Cidade
        Cidade c = new Cidade();
        c.setNome("Campinas");
        c.setEstado(em.find(Estado.class, 4));
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf("811.633.970-24");
        pf.setEmail("jose@gmail.com");
        pf.setNascimento(Calendar.getInstance());
        pf.setNome("José Pavani");
        pf.setNomeUsuario("josec");
        pf.setRg("12458596");
        pf.setSenha("j1234");
        pf.setTelefone("(22)2774-4585");
        PessoaFisica pf = em.find(PessoaFisica.class, 7);
        
        -- TipoEndereco
        TipoEndereco tp = new TipoEndereco();
        TipoEndereco tp1 = new TipoEndereco();
        tp.setDescricao("Residêncial");
        tp1.setDescricao("Empresarial");
        
        --Endereco
        PessoaFisica pf = em.find(PessoaFisica.class, 7);
        Endereco e = new Endereco();
        e.setBairro("Centro");
        e.setCep("27935-235");
        e.setComplemento("Ap 405");
        e.setEndereco("Rua Monteiro Lobato");
        e.setNickname("casa");
        e.setNumero("326");
        e.setReferencia("Perto Mercado");
        e.setTipoEndereco(em.find(TipoEndereco.class, 8));
        pf.adicionarEndereco(e);
        
        -- Categoria e Produto
        Categoria c = new Categoria();
        c.setNome("Eletrônicos");        
        Marca c = new Marca();
        c.setNome("Samsung");

        -- Produto
        Produto p  = new Produto();
        p.setCategoria(em.find(Categoria.class, 1));
        p.setMarca(em.find(Marca.class, 3));
        p.setNome("Teclado");
        p.setDescricao("padrão");
        p.setPreco(50.40);
        p.setQuantidadeEstoque(200.0);
        
        -- ManyToMany Pessoa x Produto
        PessoaFisica pf = em.find(PessoaFisica.class, 7);
        Produto p = em.find(Produto.class, 8);
        pf.getDesejos().add(p);
        
        -- Pessoa Jurídica
        PessoaJuridica jr = new PessoaJuridica();
        jr.setCnpj("08.472.686/0001-25");
        jr.setIe("1258423");
        jr.setNome("Campos Pavani de Macaé");
        jr.setEmail("jpavani@gmail.com");
        jr.setTelefone("(22)3737-0460"); 

        -- Permissao
        Permissao p1 = new Permissao();
        p1.setNome("Administrador");
        p1.setDescricao("Administrador do sistema");
        Permissao p2 = new Permissao();
        p2.setNome("Usuario");
        p2.setDescricao("Usuario do sistema");
        
        -- @ManyToMany PessoaFisica x Permissao 
        PessoaFisica pf = em.find(PessoaFisica.class, 7);
        Permissao p = em.find(Permissao.class, "Administrador");
        Permissao p1 = em.find(Permissao.class, "Usuario");
        pf.getPermissoes().add(p);
        pf.getPermissoes().add(p1);
        
        -- Venda e ItenVenda
        PessoaFisica pf = em.find(PessoaFisica.class, 7);
        Produto p = em.find(Produto.class, 8);
        Venda v = new Venda();
        v.setPessoaFissica(pf);
        v.setData(Calendar.getInstance());
        v.setParcelas(1);
        
        VendaItens item = new VendaItens();
        item.setProduto(p);
        item.setQuantidade(2.0);
        item.setValorUnitario(p.getPreco());
        item.setValorTotal(item.getQuantidade() * item.getValorUnitario());
        v.adicionarItem(item);
        
        -- Parcela Vendas         
        Venda v = em.find(Venda.class, 11);
        v.gerarParcelas();
        
        -- Compra e CompraItem
        
        PessoaJuridica pj = em.find(PessoaJuridica.class, 10);
        Produto p = em.find(Produto.class, 8);
        Compra compra = new Compra();
        CompraID id = new CompraID();
        id.setPessoaJuridica(pj);
        id.setNumeroNota(3596);
        compra.setId(id);
        compra.setData(Calendar.getInstance());
        CompraItem item = new CompraItem();
        item.setProduto(p);
        item.setValorUnitario(p.getPreco());
        item.setQuantidade(5.0);
        item.setValorTotal(item.getQuantidade() * item.getValorUnitario());
        compra.adicionarItem(item);
        
        -- Foto         
        
        Produto p = em.find(Produto.class, 9);
        Foto f = new Foto();
        f.setNome("teclado");
        f.setDescricao("KB111");
        Path path = Paths.get("C:\\Users\\sitef\\Documents\\teclado.jpg");
        f.setArquivo(Files.readAllBytes(path));
        p.adicionarFoto(f);
        */
        Foto f = em.find(Foto.class, 14);
        File file = new File("C:\\\\Users\\\\sitef\\\\Documents\\\\teclado1.jpg");
        FileOutputStream out = new FileOutputStream(file);
        out.write(f.getArquivo());
        out.close();

        //Verificar se existe erro de validação, 
        //Obs.: com o objeto ValidatorComo só vou utilizar o método persister se não existir erro de validação 
        /* Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Pais>> erros = validador.validate(p);
        if (erros.size() > 0) {
            for (ConstraintViolation<Pais> erro : erros) {
                System.out.println("Erro: " + erro.getMessage());
            }
        } else {
            em.persist(p);
        }*/
        //em.persist(p);
       // em.persist(p2);
       em.getTransaction().commit();
       em.close();
       emf.close();
    }

}
