/* 
 * Copyright (c) 2008-2010, Hazel Ltd. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hazelcast.security;

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Properties;

import javax.security.auth.Subject;

import com.hazelcast.config.SecurityConfig;

/**
 * IPermissionPolicy is used to determine any {@link Subject}'s 
 * permissions to perform a security sensitive Hazelcast operation.
 *
 */
public interface IPermissionPolicy {
	
	/**
	 * Configures {@link IPermissionPolicy}.
	 * @param securityConfig Hazelcast {@link SecurityConfig}
	 * @param properties
	 */
	void configure(SecurityConfig securityConfig, Properties properties);
	
	/**
	 * Determines permissions of subject. 
	 * @param subject 
	 * @param type of permissions in PermissionCollection
	 * @return PermissionCollection containing subject's permissions 
	 */
	PermissionCollection getPermissions(Subject subject, Class<? extends Permission> type);
	
	/**
	 * Destroys {@link IPermissionPolicy}.
	 */
	void destroy();
}
