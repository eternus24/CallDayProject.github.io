










src/main/java
 └── com.spring.boot
     ├── SpringBootBoardApplication.java   ← 메인 실행 클래스
     ├── controller
     │     └── ExerciseController.java     ← 운동 컨트롤러
     ├── dto
     │     └── HealthDTO.java            ← 전체 데이터 객체
     ├── mapper
     │     └── HealthMapper.java         ← 전체매퍼 인터페이스
     ├── service
     │     ├── ExerciseService.java        ← 서비스 인터페이스
     │     └── ExerciseServiceImpl.java    ← 서비스 구현체
     └── util
           └── MyUtil.java                 ← 공용 유틸 (페이징 등)

src/main/resources
 └── mapper
      └── exerciseMapper.xml               ← MyBatis SQL 매퍼























<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.spring.boot.mapper.ExerciseMapper">

    <!-- 부위별 운동 목록 -->
    <select id="getExercisesByPart" parameterType="string" resultType="com.spring.boot.dto.ExerciseDTO">
        SELECT id, part, name, target_muscle AS targetMuscle,
               effect, method, youtube_url AS youtubeUrl
        FROM exercise
        WHERE part = #{part}
        ORDER BY id ASC
    </select>

    <!-- 운동 상세 -->
    <select id="getExerciseById" parameterType="int" resultType="com.spring.boot.dto.ExerciseDTO">
        SELECT id, part, name, target_muscle AS targetMuscle,
               effect, method, youtube_url AS youtubeUrl
        FROM exercise
        WHERE id = #{id}
    </select>

    <!-- 운동 추가 -->
    <insert id="insertExercise" parameterType="com.spring.boot.dto.ExerciseDTO">
        INSERT INTO exercise (part, name, target_muscle, effect, method, youtube_url)
        VALUES (#{part}, #{name}, #{targetMuscle}, #{effect}, #{method}, #{youtubeUrl})
    </insert>

    <!-- 운동 수정 -->
    <update id="updateExercise" parameterType="com.spring.boot.dto.ExerciseDTO">
        UPDATE exercise
        SET part = #{part},
            name = #{name},
            target_muscle = #{targetMuscle},
            effect = #{effect},
            method = #{method},
            youtube_url = #{youtubeUrl}
        WHERE id = #{id}
    </update>

    <!-- 운동 삭제 -->
    <delete id="deleteExercise" parameterType="int">
        DELETE FROM exercise WHERE id = #{id}
    </delete>

</mapper>
