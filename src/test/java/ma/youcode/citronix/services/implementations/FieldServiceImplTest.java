package ma.youcode.citronix.services.implementations;

import ma.youcode.citronix.dto.request.field.FieldCreateDTO;
import ma.youcode.citronix.dto.response.field.FieldResponseDTO;
import ma.youcode.citronix.entities.Farm;
import ma.youcode.citronix.entities.Field;
import ma.youcode.citronix.exceptions.field.FieldSurfaceToLargeException;
import ma.youcode.citronix.exceptions.field.MaxFieldsException;
import ma.youcode.citronix.mappers.FieldMapper;
import ma.youcode.citronix.repositories.FieldRepository;
import ma.youcode.citronix.services.interfaces.FarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FieldMapper fieldMapper;

    @Mock
    private FarmService farmService;


    @InjectMocks
    private FieldServiceImpl fieldService;

    private  Field field;
    private  Farm farm;
    private  FieldResponseDTO responseDTO;
    private  FieldCreateDTO createDTO;

    @BeforeEach
    public void setUp() {

        field = new Field();
        field.setId(1L);
//        field.setSurface(1000);
        farm = new Farm();
        farm.setId(1L);
        farm.setSurface(2500);

        responseDTO = new FieldResponseDTO(1L, 1000 , null, new ArrayList<>());
        createDTO = new FieldCreateDTO(1000 , 1L);


    }

    @Test
    public void testCaseCreateField_success() {

        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
        when(fieldMapper.fromCreateDTO(createDTO)).thenReturn(field);
        field.setFarm(farm);
        when(fieldRepository.save(field)).thenReturn(field);
        when(fieldMapper.toResponseDTO(field)).thenReturn(responseDTO);

        FieldResponseDTO result = fieldService.create(createDTO);

        assertNotNull(result);

        assertEquals(1000, result.surface());
        verify(farmService).getFarmById(farm.getId());
        verify(fieldMapper).fromCreateDTO(createDTO);
        verify(fieldMapper).toResponseDTO(field);
        verify(fieldRepository).save(field);
    }

    @Test
    public void testCaseCreateField_whenFarmSpaceHasNoSpace() {
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
        when(fieldRepository.computeFieldsSurface(1L)).thenReturn(2000);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> fieldService.create(createDTO));

        assertNotNull(e.getMessage());
        assertEquals(e.getMessage(), "This any space for this new field on farm " + farm.getName());
        verify(farmService).getFarmById(farm.getId());
        verify(fieldRepository).computeFieldsSurface(1L);
        verifyNoInteractions(fieldMapper);
    }

    @Test
    public void testCaseCreateField_whenExceedFieldMaxSurface() {

        List<Field> fields = new ArrayList<>();
        for (int i = 1 ; i <= 10 ; i++) {
            fields.add(new Field());
        }

        farm.setFields(fields);
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
        when(fieldRepository.computeFieldsSurface(1L)).thenReturn(1000);

        MaxFieldsException e = assertThrows(MaxFieldsException.class, () -> fieldService.create(createDTO));

        assertNotNull(e.getMessage());
        assertEquals("Farm cannot have more than 10 fields", e.getMessage());
        verify(farmService).getFarmById(farm.getId());
        verify(fieldRepository).computeFieldsSurface(1L);
        verifyNoInteractions(fieldMapper);

    }

    @Test
    public void testCaseCreateField_whenInvalidFieldSurface() {
        createDTO = new FieldCreateDTO(1700 , 1L);
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
        FieldSurfaceToLargeException e = assertThrows(FieldSurfaceToLargeException.class, () -> fieldService.create(createDTO));

        assertNotNull(e.getMessage());
        assertEquals("Field surface cannot be greater than 1250 m²", e.getMessage());
        verify(farmService).getFarmById(farm.getId());
        verify(fieldRepository).computeFieldsSurface(1L);

        verifyNoInteractions(fieldMapper);

    }




}