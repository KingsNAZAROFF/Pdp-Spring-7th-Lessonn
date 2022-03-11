package uz.pdp.pdpspring7thlessonn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring7thlessonn.entity.Lavozim;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.LavozimDto;
import uz.pdp.pdpspring7thlessonn.repository.LavozimRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimService {
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addLavozim(LavozimDto lavozimDto) {
        boolean byName = lavozimRepository.existsByName(lavozimDto.getName());
        if (byName){
            return new ApiResponse("Bunday lavozim mavjud",false);
        }
        Lavozim lavozim = new Lavozim();
        lavozim.setName(lavozimDto.getName());
        lavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozim.setDescription(lavozimDto.getDescription());
        lavozimRepository.save(lavozim);
        return new ApiResponse("Saqlandi",true);
        /*
        {"name": "Muharrir",
        "description" : "Maqola yozadi",
        "huquqlist":[
          "ADD_POST",
          "EDIT_POST",
          "DELETE_POST",
          "ADD_COMMENT",
          "EDIT_COMMENT",
          "DELETE_COMMENT",
          "DELETE_MY_COMMENT"
        ]
         */

    }
    public List<Lavozim> getALlLavozims(){
        List<Lavozim> all = lavozimRepository.findAll();
        return all;
    }

    public ApiResponse editlavozim(Long id, LavozimDto lavozimDto) {

        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(id);
        if (optionalLavozim.isEmpty()){
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        }
        Lavozim editingLavozim = optionalLavozim.get();
        editingLavozim.setName(lavozimDto.getName());
        editingLavozim.setDescription(lavozimDto.getDescription());
        editingLavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepository.save(editingLavozim);
        return new ApiResponse("Lavozim ma'lumotlari o'zgartirildi",true);

    }
    public ApiResponse deleteLavozim(Long id){
        lavozimRepository.deleteById(id);
        return new ApiResponse("Lavozim o'chirildi !",false);
    }
}
