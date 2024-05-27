// You should use follow @SpringBootTest with RANDOM_PORT for the web environment, or you test will be failed.
// You should use @ExtendWith(SpringExtension::class) for the test class.
// For example:
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class /* Here some be {ControllerName} */ ControllerTest {
    private lateinit var mockMvc: MockMvc

    // other some mock beans

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(/* {ControllerName} */Controller(/* some mock beans */)).build()
    }

    // the test methods
}
