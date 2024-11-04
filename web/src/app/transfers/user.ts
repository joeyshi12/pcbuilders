// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.2
//   protoc               v5.28.2
// source: transfers/user.proto

/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "transfers";

export interface UserProfile {
  username?: string | undefined;
  displayName?:
    | string
    | undefined;
  /** Set only for authentication requests */
  password?: string | undefined;
}

function createBaseUserProfile(): UserProfile {
  return { username: "", displayName: "", password: "" };
}

export const UserProfile = {
  encode(message: UserProfile, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.username !== undefined && message.username !== "") {
      writer.uint32(10).string(message.username);
    }
    if (message.displayName !== undefined && message.displayName !== "") {
      writer.uint32(18).string(message.displayName);
    }
    if (message.password !== undefined && message.password !== "") {
      writer.uint32(26).string(message.password);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserProfile {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserProfile();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          message.username = reader.string();
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.displayName = reader.string();
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.password = reader.string();
          continue;
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skipType(tag & 7);
    }
    return message;
  },

  fromJSON(object: any): UserProfile {
    return {
      username: isSet(object.username) ? globalThis.String(object.username) : "",
      displayName: isSet(object.displayName) ? globalThis.String(object.displayName) : "",
      password: isSet(object.password) ? globalThis.String(object.password) : "",
    };
  },

  toJSON(message: UserProfile): unknown {
    const obj: any = {};
    if (message.username !== undefined && message.username !== "") {
      obj.username = message.username;
    }
    if (message.displayName !== undefined && message.displayName !== "") {
      obj.displayName = message.displayName;
    }
    if (message.password !== undefined && message.password !== "") {
      obj.password = message.password;
    }
    return obj;
  },

  create<I extends Exact<DeepPartial<UserProfile>, I>>(base?: I): UserProfile {
    return UserProfile.fromPartial(base ?? ({} as any));
  },
  fromPartial<I extends Exact<DeepPartial<UserProfile>, I>>(object: I): UserProfile {
    const message = createBaseUserProfile();
    message.username = object.username ?? "";
    message.displayName = object.displayName ?? "";
    message.password = object.password ?? "";
    return message;
  },
};

type Builtin = Date | Function | Uint8Array | string | number | boolean | undefined;

export type DeepPartial<T> = T extends Builtin ? T
  : T extends globalThis.Array<infer U> ? globalThis.Array<DeepPartial<U>>
  : T extends ReadonlyArray<infer U> ? ReadonlyArray<DeepPartial<U>>
  : T extends {} ? { [K in keyof T]?: DeepPartial<T[K]> }
  : Partial<T>;

type KeysOfUnion<T> = T extends T ? keyof T : never;
export type Exact<P, I extends P> = P extends Builtin ? P
  : P & { [K in keyof P]: Exact<P[K], I[K]> } & { [K in Exclude<keyof I, KeysOfUnion<P>>]: never };

function isSet(value: any): boolean {
  return value !== null && value !== undefined;
}
