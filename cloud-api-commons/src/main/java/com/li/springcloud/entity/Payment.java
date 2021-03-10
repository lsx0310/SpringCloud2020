package com.li.springcloud.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Payment
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/17 17:41
 * @Version v1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends Model<Payment> {
    
    private Long id;
    private String serial;
    

}
