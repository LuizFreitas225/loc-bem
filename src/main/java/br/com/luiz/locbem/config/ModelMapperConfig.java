package br.com.luiz.locbem.config;

import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    modelMapper.getConfiguration().setSkipNullEnabled(true);

    modelMapper.typeMap(Oferta.class, OfertaComPontuacao.class).addMappings(mapper -> {
      mapper.skip(OfertaComPontuacao::setLinkImagens);
    });

    return modelMapper;
  }


}
