package com.sh.app.schedule.dto;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.dto.TheaterDto;
import lombok.Data;

//0215
/**
 * schedule entity 기반 dto만드는 방법
 * 1.schedule 의 필드를 갖고온다.[의존이나 관계 빼고 필드만 갖고온다. id,theater..총 6개]
 * 2.private Member member;을 private String memberId;로 수정한다.
 * [내 맘대로 커스텀 할 수 있다는 것,]
 * modelMapper는 id,title,content,createdAt을 그대로 카피할 수있음.
 * 근데 내가 변경한 memberId와 attachCount는 매퍼가 인식을 못해서
 * 내가 따로 작업을 해줘야한다. boardService단의 메소드에 가서 수정함.
 */
@Data
public class ScheduleDto {

    private long id;

    private TheaterDto theater;

    private Movie movie;

    private String schDate;

    private String time;
}
