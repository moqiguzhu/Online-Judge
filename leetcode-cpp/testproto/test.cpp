#include "test.pb.h"
#include "iostream"
// #include "google/protobuf/any.h"
// #include "google/protobuf/empty.pb.h"
// #include "google/protobuf/generated_enum_reflection.h"
// #include "google/protobuf/map_field.h"
// #include "google/protobuf/metadata_lite.h"
// #include "google/protobuf/repeated_ptr_field.h"
// #include "google/protobuf/timestamp.pb.h"
// #include "google/protobuf/wrappers.pb.h"
// #include "google/protobuf/any.pb.h"
// #include "google/protobuf/descriptor_database.h"
// #include "google/protobuf/generated_enum_util.h"
// #include "google/protobuf/generated_message_util.h"
// #include "google/protobuf/map_field_inl.h"
// #include "google/protobuf/parse_context.h"
// #include "google/protobuf/service.h"
// #include "google/protobuf/descriptor.h"
// #include "google/protobuf/explicitly_constructed.h"
// #include "google/protobuf/generated_message_bases.h"
// #include "google/protobuf/has_bits.h"
// #include "google/protobuf/map_field_lite.h"
// #include "google/protobuf/source_context.pb.h"
// #include "google/protobuf/type.pb.h"
// #include "google/protobuf/api.pb.h"
// #include "google/protobuf/descriptor.pb.h"
// #include "google/protobuf/extension_set.h"
// #include "google/protobuf/generated_message_reflection.h"
// #include "google/protobuf/implicit_weak_message.h"
// #include "google/protobuf/map.h"
// #include "google/protobuf/port.h"
// #include "google/protobuf/extension_set_inl.h"
// #include "google/protobuf/generated_message_table_driven.h"
// #include "google/protobuf/inlined_string_field.h"
// #include "google/protobuf/map_type_handler.h"
// #include "google/protobuf/struct.pb.h"
// #include "google/protobuf/unknown_field_set.h"
// #include "google/protobuf/arena.h"
// #include "google/protobuf/duration.pb.h"
// #include "google/protobuf/field_access_listener.h"
// #include "google/protobuf/generated_message_table_driven_lite.h"
// #include "google/protobuf/message.h"
// #include "google/protobuf/reflection.h"
// #include "google/protobuf/arena_impl.h"
// #include "google/protobuf/field_mask.pb.h"
// #include "google/protobuf/generated_message_tctable_decl.h"
// #include "google/protobuf/map_entry.h"
// #include "google/protobuf/message_lite.h"
// #include "google/protobuf/reflection_ops.h"
// #include "google/protobuf/wire_format.h"
// #include "google/protobuf/arenastring.h"
// #include "google/protobuf/dynamic_message.h"
// #include "google/protobuf/generated_message_tctable_impl.h"
// #include "google/protobuf/map_entry_lite.h"
// #include "google/protobuf/metadata.h"
// #include "google/protobuf/repeated_field.h"
// #include "google/protobuf/text_format.h"
// #include "google/protobuf/wire_format_lite.h"

using namespace std;

int main() {
    test::A a;
    bool res = a.ParseFromString("");
    cout << res << endl;
}