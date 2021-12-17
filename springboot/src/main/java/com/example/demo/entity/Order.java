package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

    private Integer objectId;

    private Integer borrowerId;

    private String status;

    private Integer rentTatol;

    private LocalTime lentoutTime;

    private LocalTime returnTime;

    private LocalTime createdTime;

    private LocalTime finishedTime;


}
