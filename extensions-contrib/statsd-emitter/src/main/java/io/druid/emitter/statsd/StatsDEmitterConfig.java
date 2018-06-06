/*
 * Licensed to Metamarkets Group Inc. (Metamarkets) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Metamarkets licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.druid.emitter.statsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class StatsDEmitterConfig
{
  @JsonProperty
  final private String hostname;
  @JsonProperty
  final private Integer port;
  @JsonProperty
  final private String prefix;
  @JsonProperty
  final private String separator;
  @JsonProperty
  final private Boolean includeHost;
  @JsonProperty
  private final String dimensionMapPath;
  @JsonProperty
  private final String blankHolder;
  @JsonProperty
  private final StatsDEventHandler eventHandler;

  @JsonCreator
  public StatsDEmitterConfig(
      @JsonProperty("hostname") String hostname,
      @JsonProperty("port") Integer port,
      @JsonProperty("prefix") String prefix,
      @JsonProperty("separator") String separator,
      @JsonProperty("includeHost") Boolean includeHost,
      @JsonProperty("dimensionMapPath") String dimensionMapPath,
      @JsonProperty("blankHolder") String blankHolder,
      @JsonProperty("eventHandler") StatsDEventHandler eventHandler
  )
  {
    this.hostname = Preconditions.checkNotNull(hostname, "StatsD hostname cannot be null.");
    this.port = Preconditions.checkNotNull(port, "StatsD port cannot be null.");
    this.prefix = prefix != null ? prefix : "";
    this.separator = separator != null ? separator : ".";
    this.includeHost = includeHost != null ? includeHost : false;
    this.dimensionMapPath = dimensionMapPath;
    this.blankHolder = blankHolder != null ? blankHolder : "-";
    this.eventHandler = eventHandler != null ? eventHandler : new DefaultStatsDEventHandler(); // for backwards compatibility
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatsDEmitterConfig that = (StatsDEmitterConfig) o;
    return Objects.equals(hostname, that.hostname) &&
           Objects.equals(port, that.port) &&
           Objects.equals(prefix, that.prefix) &&
           Objects.equals(separator, that.separator) &&
           Objects.equals(includeHost, that.includeHost) &&
           Objects.equals(dimensionMapPath, that.dimensionMapPath) &&
           Objects.equals(blankHolder, that.blankHolder) &&
           Objects.equals(eventHandler, that.eventHandler);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(hostname, port, prefix, separator, includeHost, dimensionMapPath, blankHolder, eventHandler);
  }

  @JsonProperty
  public String getHostname()
  {
    return hostname;
  }

  @JsonProperty
  public int getPort()
  {
    return port;
  }

  @JsonProperty
  public String getPrefix()
  {
    return prefix;
  }

  @JsonProperty
  public String getSeparator()
  {
    return separator;
  }

  @JsonProperty
  public Boolean getIncludeHost()
  {
    return includeHost;
  }

  @JsonProperty
  public String getDimensionMapPath()
  {
    return dimensionMapPath;
  }

  @JsonProperty
  public String getBlankHolder()
  {
    return blankHolder;
  }

  @JsonProperty
  public StatsDEventHandler getEventHandler()
  {
    return eventHandler;
  }
}
