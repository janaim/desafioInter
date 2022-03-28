import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.repository.EmpresaRepository;
import com.example.desafioInter.service.EmpresaService;
import com.example.desafioInter.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class EmpresaTest {

    @Mock
    private EmpresaRepository empresaReposMock;

    @InjectMocks
    private EmpresaService empresaServiceMock;

    @Test
    public void create(){

        Empresa insereEmpresa = Empresa.builder()
                .nomeEmpresa("Magazine Luiza S.A.")
                .nomeAcao("Magazine Luisa")
                .preco((float) 15.00)
                .quantidadeAcao(0)
                .status(true)
                .ticker("MGLU3")
                .build();

        Empresa empresa = new Empresa();


       empresa.setNomeEmpresa(insereEmpresa.getNomeEmpresa());
       empresa.setNomeAcao(insereEmpresa.getNomeAcao());
       empresa.setPreco(insereEmpresa.getPreco());
       empresa.setQuantidadeAcao(insereEmpresa.getQuantidadeAcao());
       empresa.setStatus(insereEmpresa.getStatus());
       empresa.setTicker(insereEmpresa.getTicker());


        Mockito.lenient().when(empresaReposMock.save(insereEmpresa)).thenReturn(empresa);

        Empresa result = empresaServiceMock.inserirNovaEmpresa(insereEmpresa);

        Assertions.assertNotNull(result);

    }

    @Test
    public void create_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            Empresa insereEmpresa = Empresa.builder()
                    .nomeEmpresa("Magazine Luiza S.A.")
                    .nomeAcao("Magazine Luisa")
                    .preco((float) 15.00)
                    .quantidadeAcao(0)
                    .status(true)
                    .ticker("MGLU3")
                    .build();

            Empresa empresa = new Empresa();

            Mockito.lenient().when(empresaReposMock.save(insereEmpresa)).thenReturn(empresa);

            empresaServiceMock.inserirNovaEmpresa(insereEmpresa);
        } );

        Assertions.assertNotNull(exception);
    }


    @Test
    public void updatePreco(){

        long id = 1;

        Empresa alteraEmpresa = Empresa.builder()
                .nomeEmpresa("Magazine Luiza S.A.")
                .nomeAcao("Magazine Luisa")
                .preco((float) 15.00)
                .quantidadeAcao(0)
                .status(true)
                .ticker("MGLU3")
                .build();

        Empresa empresa = new Empresa();

        empresa.setPreco(alteraEmpresa.getPreco());

        Mockito.lenient().when(empresaReposMock.save(alteraEmpresa)).thenReturn(empresa);

        Empresa result = empresaServiceMock.alterarPrecoAcao(id, empresa.getPreco());

        Assertions.assertNotNull(result);

    }

    @Test
    public void updatePreco_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            Empresa alteraEmpresa = Empresa.builder()
                    .nomeEmpresa("Magazine Luiza S.A.")
                    .nomeAcao("Magazine Luisa")
                    .preco((float) 15.00)
                    .quantidadeAcao(0)
                    .status(true)
                    .ticker("MGLU3")
                    .build();

            Empresa empresa = new Empresa();

            Mockito.lenient().when(empresaReposMock.save(alteraEmpresa)).thenReturn(empresa);

            empresaServiceMock.alterarPrecoAcao((long) 1, empresa.getPreco());
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void updateStatus(){

        long id = 1;

        Empresa alteraEmpresa = Empresa.builder()
                .nomeEmpresa("Magazine Luiza S.A.")
                .nomeAcao("Magazine Luisa")
                .preco((float) 15.00)
                .quantidadeAcao(0)
                .status(true)
                .ticker("MGLU3")
                .build();

        Empresa empresa = new Empresa();

        empresa.setStatus(alteraEmpresa.getStatus());

        Mockito.lenient().when(empresaReposMock.save(alteraEmpresa)).thenReturn(empresa);

        Empresa result = empresaServiceMock.alterarStatusEmpresa(id, empresa.getStatus());

        Assertions.assertNotNull(result);

    }

    @Test
    public void updateStatus_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            Empresa alteraEmpresa = Empresa.builder()
                    .nomeEmpresa("Magazine Luiza S.A.")
                    .nomeAcao("Magazine Luisa")
                    .preco((float) 15.00)
                    .quantidadeAcao(0)
                    .status(true)
                    .ticker("MGLU3")
                    .build();

            Empresa empresa = new Empresa();

            Mockito.lenient().when(empresaReposMock.save(alteraEmpresa)).thenReturn(empresa);

            empresaServiceMock.alterarStatusEmpresa((long) 1, empresa.getStatus());
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findById(){

        long id = 2;

        Optional<Empresa> empresa = Optional.of(new Empresa());

        Mockito.when(empresaReposMock.findById(ArgumentMatchers.eq(id))).thenReturn(empresa);

        Empresa result = empresaServiceMock.buscarAcaoPorId(id);

        Assertions.assertNotNull(result);

    }

    @Test
    public void findById_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            Optional<Empresa> empresa = Optional.empty();

            Mockito.when(empresaReposMock.findById(ArgumentMatchers.anyLong())).thenReturn(empresa);

            empresaServiceMock.buscarAcaoPorId((long) 32);
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findByNome(){

        String nome = "Ação Genérica";

        Optional<Empresa> empresa = Optional.of(new Empresa());

        Mockito.when(empresaReposMock.findByNomeAcao(ArgumentMatchers.eq(nome))).thenReturn(empresa);

        Empresa result = empresaServiceMock.buscarAcaoPorNome(nome);

        Assertions.assertNotNull(result);

    }

    @Test
    public void findByNome_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            Optional<Empresa> empresa = Optional.empty();

            Mockito.when(empresaReposMock.findByNomeAcao(ArgumentMatchers.anyString())).thenReturn(empresa);

            empresaServiceMock.buscarAcaoPorNome("Ação Genérica");
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findByStatus(){

        boolean status = true;

        List<Empresa> empresa = new ArrayList<>();

        Mockito.when(empresaReposMock.findAllByStatus(ArgumentMatchers.eq(status))).thenReturn(empresa);

        List<Empresa> result = empresaServiceMock.buscarAcoesPorStatus(status);

        Assertions.assertNotNull(result);

    }

    @Test
    public void findByStatus_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            List<Empresa> empresas = new ArrayList<>();

            Mockito.when(empresaReposMock.findAllByStatus(ArgumentMatchers.anyBoolean())).thenReturn(empresas);

            empresaServiceMock.buscarAcoesPorStatus(true);
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findAll(){

        List<Empresa> empresas = new ArrayList<>();

        Mockito.when(empresaReposMock.findAll()).thenReturn(empresas);

        List<Empresa> result = empresaServiceMock.buscarTodasAsAcoes();

        Assertions.assertNotNull(result);

    }

    @Test
    public void findAll_notFound_shouldReturnEmpty(){


        List<Empresa> empresas = Collections.emptyList();

        Mockito.when(empresaReposMock.findAll()).thenReturn(empresas);

        List<Empresa> result = empresaServiceMock.buscarTodasAsAcoes();

        Assertions.assertNotNull(result);
    }

    @Test
    public void deleteById(){

//        long id = 2;
//
//        Optional<Cliente> clientes = Optional.of(new Cliente());
//
//        Mockito.doNothing().when(clienteRepoMock.deleteById(ArgumentMatchers.eq(id)));
//
//        clienteServiceMock.excluirClientePorId(id);
//
//        Assertions.assertDoesNotThrow(null);

    }

    @Test
    public void deleteById_notFound_shouldReturnEmpty(){

//        List<Cliente> cliente = Collections.emptyList();
//
//        Mockito.doNothing().when(clienteRepoMock.deleteById(ArgumentMatchers.anyLong()));
//
//
//
//
//        Assertions.assertNotNull(clienteServiceMock.excluirClientePorId((long) 32));
    }


}
