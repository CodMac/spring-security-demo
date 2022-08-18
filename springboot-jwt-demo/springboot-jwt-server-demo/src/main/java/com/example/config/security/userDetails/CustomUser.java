/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.config.security.userDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.io.Serializable;

/**
 * 当前登录用户
 */
@Getter
public class CustomUser implements Serializable {

	private final long id;

	private final String code;

	@JsonIgnore
	private final String pwd;

	@JsonCreator
	public CustomUser(long id, String code, String pwd) {
		this.id = id;
		this.code = code;
		this.pwd = pwd;
	}

}
