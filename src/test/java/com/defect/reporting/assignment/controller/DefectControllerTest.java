package com.defect.reporting.assignment.controller;

import com.defect.reporting.assignment.service.DefectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Safwan
 */

@RunWith(SpringRunner.class)
@WebMvcTest(DefectController.class)
public class DefectControllerTest {

    @Mock
    org.springframework.web.servlet.View mockView;

    private MockMvc mockMvc;

    @InjectMocks
    private DefectController defectController;

    @MockBean
    private DefectService defectService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(defectController).setSingleView(mockView).build();
    }

    @Test
    public void shallReturnOkResponseForDefectList() throws Exception {
        mockMvc.perform(get("/defect/getAll")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnNotFound() throws Exception {
        mockMvc.perform(get("/defect/getAl")).andExpect(status().isNotFound());
    }

    @Test
    public void shallReturnOkResponseForGetDefect() throws Exception {
        mockMvc.perform(get("/defect/getDefect/2")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnBadRequestForGetDefect() throws Exception {
        mockMvc.perform(get("/defect/getDefect")).andExpect(status().isNotFound());
    }

    @Test
    public void shallReturnOkResponseForCreatingDefect() throws Exception {
        mockMvc.perform(post("/defect/createDefect?machine_id=1&personal_number=2&description=hello&defectStatus=2")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnBadRequestForCreatingDefect() throws Exception {
        mockMvc.perform(post("/defect/createDefect?machine_id=1&personal_number=2")).andExpect(status().isBadRequest());
    }

    @Test
    public void shallReturnOkResponseForUpdatingDefectStatus() throws Exception {
        mockMvc.perform(patch("/defect/updateDefectStatus?defectId=1&defectStatus=1")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnBadRequestForUpdatingDefectStatus() throws Exception {
        mockMvc.perform(patch("/defect/updateDefectStatus?defectId=1")).andExpect(status().isBadRequest());
    }
}
