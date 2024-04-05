package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "file")
public class File  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer  id;

        @Column(name = "name")
        private String name;

        @Column(name = "url")
        private String url;

        public File(String name, String url) {
                this.name=name;
                this.url=url;
        }

}
