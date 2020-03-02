package com.skillbox.sw.mapper;

import com.skillbox.sw.api.response.MessageApi;
import com.skillbox.sw.domain.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper(
        uses = { DateMapper.class,
                PersonMapper.class},
        imports = {
                LocalDate.class,
                ZoneOffset.class
        }
)
public interface MessageMapper extends BaseMapper {

        @Mapping(target = "authorId", source = "message.author.id")
        MessageApi messageToMessageApi(Message message);
}
