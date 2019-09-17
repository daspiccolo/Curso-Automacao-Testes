package tests.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}.
 *
 */
public class GerenciadoraContasTest_Ex11 {

    private GerenciadoraContas gerContas;

    /**
     * Teste básico da transferência de um valor da conta de um cliente para outro,
     * estando ambos os clientes ativos e havendo saldo suficiente para tal transferência
     * ocorrer com sucesso.
     *
     */
    @Test
    public void testTransfereValor() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, 200, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, 100, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertThat(conta02.getSaldo(), is(100.0));
        assertThat(conta01.getSaldo(), is(100.0));
    }

    /**
     * Teste básico da tentativa de transferência de um valor da conta de um cliente para outro
     * quando não há saldo suficiente, mas o saldo é positivo.
     *
     */
    @Test
    public void testTransfereValor_SaldoInsuficiente() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, 100, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertThat(conta01.getSaldo(), is(-100.0));
        assertThat(conta02.getSaldo(), is(200.0));
    }

    /**
     * Teste básico da tentativa de transferência de um valor da conta de um cliente para outro
     * quando não há saldo suficientee o saldo é negativo.
     *
     */
    @Test
    public void testTransfereValor_SaldoNegativo() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertThat(conta01.getSaldo(), is(-300.0));
        assertThat(conta02.getSaldo(), is(200.0));
    }

    /**
     * Teste básico da tentativa de transferência de um valor da conta de um cliente para outro
     * quando o saldo do cliente origem é negativo e do cliente destino também é negativo.
     *
     */
    @Test
    public void testTransfereValor_SaldoNegativoParaNegativo() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertThat(conta01.getSaldo(), is(-300.0));
        assertThat(conta02.getSaldo(), is(100.0));
    }

    /**
     * Teste básico da tentativa de transferência de um valor nulo da conta de um cliente para outro.
     *
     */
    @Test
    public void testTransfereValor_Nenhum() {

        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        int idConta01 = 1;
        int idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean sucesso = gerContas.transfereValor(idConta01, 2, idConta02);

        /* ========== Verificações ========== */
        assertTrue(sucesso);
        assertThat(conta01.getSaldo(), is(-102.0));
        assertThat(conta02.getSaldo(), is(-98.0));
    }

}
// Cálculos matemáticos e financeiros