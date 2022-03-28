import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.repository.ClienteRepository;
import com.example.desafioInter.service.ClienteService;
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

import static org.springframework.http.RequestEntity.post;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class ClienteTest {

    @Mock
    private ClienteRepository clienteRepoMock;

    @InjectMocks
    private ClienteService clienteServiceMock;



        @Test
        public void findById(){

            long id = 2;

            Optional<Cliente> clientes = Optional.of(new Cliente());

            Mockito.when(clienteRepoMock.findById(id)).thenReturn(clientes);

            Cliente result = clienteServiceMock.buscarClientePorId(id);

            Assertions.assertNotNull(result);

        }

        @Test
        public void findById_notFound_shouldReturnEmpty(){

            Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

                Optional<Cliente> cliente = Optional.empty();

                Mockito.when(clienteRepoMock.findById(ArgumentMatchers.anyLong())).thenReturn(cliente);

                clienteServiceMock.buscarClientePorId((long) 32);
            } );

            Assertions.assertNotNull(exception);
        }

    @Test
    public void findByNome(){

        String nome = "João";

        Optional<Cliente> clientes = Optional.of(new Cliente());

        Mockito.when(clienteRepoMock.findByNome(nome)).thenReturn(clientes);

        Cliente result = clienteServiceMock.buscarClientePorNome(nome);

        Assertions.assertNotNull(result);

    }

    @Test
    public void findByNome_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            Optional<Cliente> cliente = Optional.empty();

            Mockito.when(clienteRepoMock.findByNome(ArgumentMatchers.anyString())).thenReturn(cliente);

            clienteServiceMock.buscarClientePorNome("João");
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findByCpf(){

        String cpf = "99999999900";

        Optional<Cliente> clientes = Optional.of(new Cliente());

        Mockito.when(clienteRepoMock.findByCpf(cpf)).thenReturn(clientes);

        Cliente result = clienteServiceMock.buscarClientePorCpf(cpf);

        Assertions.assertNotNull(result);

    }

    @Test
    public void findByCpf_notFound_shouldReturnEmpty(){

        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            Optional<Cliente> cliente = Optional.empty();

            Mockito.when(clienteRepoMock.findByCpf(ArgumentMatchers.anyString())).thenReturn(cliente);

            clienteServiceMock.buscarClientePorCpf("99999999900");
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void findAll(){

        List<Cliente> clientes = new ArrayList<>();

        Mockito.when(clienteRepoMock.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteServiceMock.buscarTodosOsClientes();

        Assertions.assertNotNull(result);

    }

    @Test
    public void findAll_notFound_shouldReturnEmpty(){


            List<Cliente> cliente = Collections.emptyList();

            Mockito.when(clienteRepoMock.findAll()).thenReturn(cliente);

            List<Cliente> result = clienteServiceMock.buscarTodosOsClientes();

        Assertions.assertNotNull(result);
    }

    @Test
    public void update(){

        long id = 1;

        Cliente alteraCliente = Cliente.builder()
                .idCliente(id)
                .nome("Carlos")
                .email("cc@email.com")
                .cpf("123")
                .build();

        Cliente cliente = new Cliente();

        cliente.setIdCliente(alteraCliente.getIdCliente());
        cliente.setNome(alteraCliente.getNome());
        cliente.setEmail(alteraCliente.getEmail());
        cliente.setCpf(alteraCliente.getCpf());
        //Optional<Cliente> clientes = Optional.of(new Cliente());


        Mockito.lenient().when(clienteRepoMock.save(alteraCliente)).thenReturn(cliente);

        Cliente result = clienteServiceMock.alterarCliente(id, alteraCliente);

        Assertions.assertNotNull(result);

    }

    @Test
    public void update_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            Cliente alteraCliente = Cliente.builder()
                    .nome("Carlos")
                    .email("cc@email.com")
                    .cpf("123")
                    .build();

            Cliente cliente = new Cliente();

            Mockito.lenient().when(clienteRepoMock.save(alteraCliente)).thenReturn(cliente);

            clienteServiceMock.alterarCliente((long) 1, alteraCliente);
        } );

        Assertions.assertNotNull(exception);
    }

    @Test
    public void create(){

        Cliente insereCliente = Cliente.builder()
                .nome("Carlos")
                .email("cc@email.com")
                .cpf("123")
                .build();

        Cliente cliente = new Cliente();


        cliente.setNome(insereCliente.getNome());
        cliente.setEmail(insereCliente.getEmail());
        cliente.setCpf(insereCliente.getCpf());


        Mockito.lenient().when(clienteRepoMock.save(insereCliente)).thenReturn(cliente);

        Cliente result = clienteServiceMock.inserirNovoCliente(insereCliente);

        Assertions.assertNotNull(result);

    }

    @Test
    public void create_notFound_shouldReturnException(){
        Exception exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            Cliente insereCliente = Cliente.builder()
                    .nome("Carlos")
                    .email("cc@email.com")
                    .cpf("123")
                    .build();

            Cliente cliente = new Cliente();

            Mockito.lenient().when(clienteRepoMock.save(insereCliente)).thenReturn(cliente);

            clienteServiceMock.alterarCliente((long) 1, insereCliente);
        } );

        Assertions.assertNotNull(exception);
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
