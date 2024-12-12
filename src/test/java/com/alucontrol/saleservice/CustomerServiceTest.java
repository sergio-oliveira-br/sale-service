package com.alucontrol.saleservice;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.service.external.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void findCustomerById_shouldReturnSucccess() {

        // Arrange: Configurar o comportamento do mock
        Long id = 1L;
        when(customerClient.findCustomerNameById(id)).thenReturn("Sergio");

        // Act: Chamar o método a ser testado
        String customerName = customerService.getCustomerNameById(id);

        // Assert: Verificar os resultados
        assertEquals("Sergio", customerName);
        verify(customerClient).findCustomerNameById(id);
    }

    @Test
    void getCustomerNameById_shouldReturnGenericMessageOnFailure() {

        // Arrange: Configura o ambiente e os mocks.
        long id = 1L;
        when(customerClient.findCustomerNameById(id)).thenThrow(new RuntimeException("Simulated failure"));

        // Act: Executa o método a ser testado.
        String customerName = customerService.getCustomerNameById(id);

        // Assert: Verificar os resultados
        assertEquals("Retornando uma mensagem genérica informando a existencia da falha no getCustomerNameById()", customerName);
        verify(customerClient).findCustomerNameById(id);
    }
}
