import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.entity.Investimento;
import com.example.desafioInter.repository.InvestimentoRepository;
import com.example.desafioInter.service.InvestimentoService;
import com.example.desafioInter.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.paukov.combinatorics3.Generator;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class InvestimentoTest {

    @Mock
    private InvestimentoRepository investimentoRepoMock;

    @InjectMocks
    private InvestimentoService investimentoServiceMock;

    @Test
    public void create(){
        List<List<Empresa>> lists = new ArrayList<>();

        Investimento insereInvestimento = Investimento.builder()
                .nomeInvestimento("Investimento do Lucas 1")
                .cliente(Cliente.builder().build())
                .dinheiroInvestido((float) 15.00)
                .quantidadeEmpresasDiversificacao(3)
                .acoesInvestidas(lists)
                .build();

        Investimento investimento = new Investimento();

        investimento.setNomeInvestimento(insereInvestimento.getNomeInvestimento());
        investimento.setCliente(investimento.getCliente());
        investimento.setDinheiroInvestido(insereInvestimento.getDinheiroInvestido());
        investimento.setAcoesInvestidas(insereInvestimento.getAcoesInvestidas());


        Mockito.lenient().when(investimentoRepoMock.save(insereInvestimento)).thenReturn(investimento);

        Investimento result = investimentoServiceMock.criarNovoInvestimentoPorCliente(insereInvestimento);

        Assertions.assertNotNull(result);
    }

    @Test
    public void create_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            List<List<Empresa>> lists = new ArrayList<>();

            Investimento insereInvestimento = Investimento.builder()
                    .nomeInvestimento("Investimento do Lucas 1")
                    .cliente(Cliente.builder().build())
                    .dinheiroInvestido((float) 15.00)
                    .quantidadeEmpresasDiversificacao(3)
                    .acoesInvestidas(lists)
                    .build();

            Investimento investimento = new Investimento();

            investimento.setNomeInvestimento(insereInvestimento.getNomeInvestimento());
            investimento.setCliente(investimento.getCliente());
            investimento.setDinheiroInvestido(insereInvestimento.getDinheiroInvestido());
            investimento.setAcoesInvestidas(insereInvestimento.getAcoesInvestidas());

            Mockito.lenient().when(investimentoRepoMock.save(insereInvestimento)).thenReturn(investimento);

            investimentoServiceMock.criarNovoInvestimentoPorCliente(insereInvestimento);
        } );

        Assertions.assertNotNull(exception);
    }

    public void criarDiversificacaoInvestimentoSolicitado(){

    }

    @Test
    public void buscarListaGeralDeInvestimentosPorCliente(){

        Cliente cliente = new Cliente();

        List<Investimento> investimento = new ArrayList<>();

        Mockito.when(investimentoRepoMock.findAllByCliente(ArgumentMatchers.eq(cliente))).thenReturn(investimento);

        List<Investimento> result = investimentoServiceMock.buscarListaGeralDeInvestimentosPorCliente(cliente);

        Assertions.assertNotNull(result);

    }

    @Test
    public void buscarListaGeralDeInvestimentosPorCliente_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            List<Investimento> investimento = new ArrayList<>();

            Mockito.when(investimentoRepoMock.findAllByCliente(ArgumentMatchers.anyObject())).thenReturn(investimento);

            investimentoServiceMock.buscarListaGeralDeInvestimentosPorCliente(Cliente.builder().build());
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void buscarInvestimentoEspecificoPorCliente(){

        Cliente cliente = new Cliente();

        Optional<Investimento> investimento = Optional.of(new Investimento());

        Mockito.when(investimentoRepoMock.findByCliente(ArgumentMatchers.eq(cliente))).thenReturn(investimento);

        Investimento result = investimentoServiceMock.buscarInvestimentoEspecificoPorCliente(cliente);

        Assertions.assertNotNull(result);

    }

    @Test
    public void buscarInvestimentoEspecificoPorCliente_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            Optional<Investimento> investimento = Optional.of(new Investimento());

            Mockito.when(investimentoRepoMock.findByCliente(ArgumentMatchers.anyObject())).thenReturn(investimento);

            investimentoServiceMock.buscarInvestimentoEspecificoPorCliente(Cliente.builder().build());
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void buscarTotalDeInvestimentosPorEmpresa(){

        Empresa acoes = new Empresa();

        List<Investimento> investimento = new ArrayList<>();

        Mockito.when(investimentoRepoMock.findAllByAcoesInvestidas(ArgumentMatchers.eq(acoes))).thenReturn(investimento);

        List<Investimento> result = investimentoServiceMock.buscarTotalDeInvestimentosPorEmpresa(acoes);

        Assertions.assertNotNull(result);

    }

    @Test
    public void buscarTotalDeInvestimentosPorEmpresa_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            List<Investimento> investimento = new ArrayList<>();

            Mockito.when(investimentoRepoMock.findAllByAcoesInvestidas(ArgumentMatchers.anyObject())).thenReturn(investimento);

            investimentoServiceMock.buscarTotalDeInvestimentosPorEmpresa(Empresa.builder().build());
        } );

        Assertions.assertNotNull(exception);
    }

}
