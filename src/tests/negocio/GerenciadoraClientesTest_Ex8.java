package tests.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import negocio.Cliente;
import negocio.GerenciadoraClientes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 *
 */
public class GerenciadoraClientesTest_Ex8 {

    private GerenciadoraClientes gerClientes;
    private int idCLiente01 = 1;
    private	int idCLiente02 = 2;

    @Before
    public void setUp() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        Cliente cliente01 = new Cliente(idCLiente01, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(idCLiente02, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 1, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        gerClientes = new GerenciadoraClientes(clientesDoBanco);

        // a) Abriu conexão com o BD? Então...
        // b) Criou arquivos e pastas aqui? Então...
        // c) Inseriu clientes fictícios na base de dados especificamente para os testes desta classe? Então...
    }

    @After
    public void tearDown() {
        gerClientes.limpa();

        // a) Fecha aqui!!!
        // b) Apaga todos eles aqui!!!
        // c) Apaga eles aqui!!!
    }

    /**
     * Teste básico da pesquisa de um cliente a partir do seu ID.
     *
     */
    @Test
    public void testPesquisaCliente() {

        /* ========== Execução ========== */
        Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);

        /* ========== Verificações ========== */
        assertThat(cliente.getId(), is(idCLiente01));

    }

    /**
     * Teste básico da remoção de um cliente a partir do seu ID.
     *
     */
    @Test
    public void testRemoveCliente() {

        /* ========== Execução ========== */
        boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);

        /* ========== Verificações ========== */
        assertThat(clienteRemovido, is(true));
        assertThat(gerClientes.getClientesDoBanco().size(), is(1));
        assertNull(gerClientes.pesquisaCliente(idCLiente02));

    }

}
// A Independência do Teste