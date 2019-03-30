//: com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapperTest.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ICategoryMapperTest {

    static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    private Category category;
    private ICategoryMapper categoryMapper;

    private Random random;
    private long id;
    private String name;
    private Timestamp createTime;
    private OffsetDateTime createTimeDTO;


    @BeforeEach
    void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextInt(10);
        this.name = UUID.randomUUID().toString();
        this.createTimeDTO = OffsetDateTime.now();
        this.createTime = Timestamp.valueOf(
                this.createTimeDTO.toLocalDateTime());
        this.category = Category.builder()
                .id(this.id)
                .name(this.name)
                .createdDate(this.createTime)
                .build();
        this.categoryMapper = ICategoryMapper.INSTANCE;
    }

    @DisplayName("Able to convert Category to CategoryDTO - ")
    @Test
    void categoryToCategoryDTO() {

        // Given
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String expectedDateTimeMessage =
                dateTimeFormatter.format(this.createTimeDTO);

        // When
        CategoryDTO categoryDTO = this.categoryMapper.categoryToCategoryDTO(
                this.category);

        // Then
        assertThat(categoryDTO)
                .as("Should be able to transform a Category to a CategoryDTO.")
                .isNotNull();
        assertThat(categoryDTO.getId()).as("Should have same id.")
                .isEqualTo(this.id);
        assertThat(categoryDTO.getName()).as("Should have same name.")
                .isEqualTo(this.name);
        assertThat(dateTimeFormatter.format(categoryDTO.getCreatedDate()))
                .as("Creation date should be '%s'.", expectedDateTimeMessage)
                .isEqualTo(expectedDateTimeMessage);
    }

    @DisplayName("Able to convert CategoryDTO back to Category - ")
    @Test
    void testCategoryDTO2Category() {

        // Given
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String expectedDateTimeMessage =
                dateTimeFormatter.format(this.createTimeDTO);

        CategoryDTO dto = CategoryDTO.builder()
                .id(this.id)
                .name(this.name)
                .createdDate(this.createTimeDTO)
                .build();

        // When
        Category category = this.categoryMapper.categoryDTOToCategory(dto);

        // Then
        assertThat(category).as("Category should not be null")
                .isNotNull();
        assertThat(category.getId())
                .as("The id of category should be %dL", this.id)
                .isEqualTo(this.id);
        assertThat(category.getName())
                .as("The name of the category should be %s", this.name)
                .isEqualTo(this.name);
        assertThat(dateTimeFormatter.format(category.getCreatedDate().toLocalDateTime()))
                .as("The created time of the category should be %s",
                        expectedDateTimeMessage)
                .isEqualTo(expectedDateTimeMessage);
    }

}///:~