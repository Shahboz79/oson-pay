//package uz.pdp.restservice.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import uz.pdp.restservice.controller.distributor.CheckDistributorController;
//
//@Component
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//
//    private final CheckDistributorController checkDistributorController;
//
//    public InterceptorConfig(CheckDistributorController checkDistributorController) {
//        this.checkDistributorController = checkDistributorController;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(checkDistributorController);
//    }
//}
