// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.kudu.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.BitSet;

import org.junit.Rule;
import org.junit.Test;

import org.apache.kudu.test.junit.RetryRule;

public class TestBitSet {

  @Rule
  public RetryRule retryRule = new RetryRule();

  /**
   * Test out BitSet-related operations
   */
  @Test
  public void test() {
    int colCount = 1;
    BitSet bs = new BitSet(colCount);
    bs.set(0);
    int size = Bytes.getBitSetSize(colCount);
    byte[] result =  Bytes.fromBitSet(bs, colCount);
    assertEquals(size, result.length);
    BitSet newBs = Bytes.toBitSet(result, 0, colCount);
    assertTrue(newBs.get(0));

    colCount = 7;
    bs = new BitSet(colCount);
    bs.set(0);
    bs.set(5);
    size = Bytes.getBitSetSize(colCount);
    result =  Bytes.fromBitSet(bs, colCount);
    assertEquals(size, result.length);
    newBs = Bytes.toBitSet(result, 0, colCount);
    assertTrue(newBs.get(0));
    assertFalse(newBs.get(1));
    assertFalse(newBs.get(2));
    assertFalse(newBs.get(3));
    assertFalse(newBs.get(4));
    assertTrue(newBs.get(5));
    assertFalse(newBs.get(6));

    colCount = 8;
    bs = new BitSet(colCount);
    bs.set(0);
    bs.set(5);
    bs.set(7);
    size = Bytes.getBitSetSize(colCount);
    result =  Bytes.fromBitSet(bs, colCount);
    assertEquals(size, result.length);
    newBs = Bytes.toBitSet(result, 0, colCount);
    assertTrue(newBs.get(0));
    assertFalse(newBs.get(1));
    assertFalse(newBs.get(2));
    assertFalse(newBs.get(3));
    assertFalse(newBs.get(4));
    assertTrue(newBs.get(5));
    assertFalse(newBs.get(6));
    assertTrue(newBs.get(7));

    colCount = 11;
    bs = new BitSet(colCount);
    bs.set(0);
    bs.set(5);
    bs.set(7);
    bs.set(9);
    size = Bytes.getBitSetSize(colCount);
    result =  Bytes.fromBitSet(bs, colCount);
    assertEquals(size, result.length);
    newBs = Bytes.toBitSet(result, 0, colCount);
    assertTrue(newBs.get(0));
    assertFalse(newBs.get(1));
    assertFalse(newBs.get(2));
    assertFalse(newBs.get(3));
    assertFalse(newBs.get(4));
    assertTrue(newBs.get(5));
    assertFalse(newBs.get(6));
    assertTrue(newBs.get(7));
    assertFalse(newBs.get(8));
    assertTrue(newBs.get(9));
    assertFalse(newBs.get(10));
  }
}
