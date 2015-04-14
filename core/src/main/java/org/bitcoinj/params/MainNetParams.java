/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 128;
        addressHeader = 56; //encodes as P in base 58
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 22228; //PointCoin has port 
                     // bitcoinj uses port 8333
        packetMagic = 0xcefacebaL; //0xbaceface is original, change endian-ness
                                   //0xf9beb4d9L is from bitcoinj
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setTime(1231006505L);
        genesisBlock.setNonce(2083236893);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 5;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(11111, new Sha256Hash("0000000069e244f73d78e8fd29ba2fd2ed618bd6fa2ee92559f542fdb26e7c1d"));
        checkpoints.put(33333, new Sha256Hash("000000002dd5588a74784eaa7ab0507a18ad16a236e7b1ce69f00d7ddfb5d0a6"));
        checkpoints.put(74000, new Sha256Hash("0000000000573993a3c9e41ce34471c079dcf5f52a0e824a81e7f953b8661a20"));
        checkpoints.put(105000, new Sha256Hash("00000000000291ce28027faea320c8d2b054b2e0fe44a773f3eefb151d6bdc97"));
        checkpoints.put(134444, new Sha256Hash("00000000000005b12ffd4cd315cd34ffd4a594f430ac814c91184a0d42d2b0fe"));
        checkpoints.put(168000, new Sha256Hash("000000000000099e61ea72015e79632f216fe6cb33d7899acb35b75c8303b763"));
        checkpoints.put(193000, new Sha256Hash("000000000000059f452a5f7340de6682a977387c17010ff6e6c3bd83ca8b1317"));
        checkpoints.put(210000, new Sha256Hash("000000000000048b95347e83192f69cf0366076336c639f9b7228e9ba171342e"));
        checkpoints.put(216116, new Sha256Hash("00000000000001b4f4b433e81ee46494af945cf96014816a4e2370f11b23df4e"));
        checkpoints.put(225430, new Sha256Hash("00000000000001c108384350f74090433e7fcf79a606b8e797f065b130575932"));
        checkpoints.put(250000, new Sha256Hash("000000000000003887df1f29024b06fc2200b55f8af8f35453d7be294df2d214"));
        checkpoints.put(267300, new Sha256Hash("000000000000000a83fbd660e918f218bf37edd92b748ad940483c7c116179ac"));
        checkpoints.put(279000, new Sha256Hash("0000000000000001ae8c72a0b0c301f67e3afca10e819efa9041e458e9bd7e40"));
        checkpoints.put(300255, new Sha256Hash("0000000000000000162804527c6e9b9f0563a280525f9d08c12041def0a0f3b2"));
		checkpoints.put(319400, new Sha256Hash("000000000000000021c6052e9becade189495d1c539aa37c58917305fd15f13b"));

        dnsSeeds = new String[] {
                "safari.bitcoin-class.org",        // Block Explorer
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
