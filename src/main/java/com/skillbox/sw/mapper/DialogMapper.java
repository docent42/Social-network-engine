package com.skillbox.sw.mapper;

import com.skillbox.sw.api.response.DialogApi;
import com.skillbox.sw.domain.Dialog;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(
        uses = {
                DateMapper.class,
                MessageMapper.class,
                PersonMapper.class
        }
)
public interface DialogMapper extends BaseMapper {

    DialogApi dialogToDialogApi(Dialog dialog);

    List<DialogApi> dialogToDialogApi(List<Dialog> dialog);
}