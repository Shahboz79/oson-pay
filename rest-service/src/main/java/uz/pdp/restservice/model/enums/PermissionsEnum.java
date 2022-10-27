package uz.pdp.restservice.model.enums;


public enum PermissionsEnum {
    SUPER_ADMIN_ADD("super_admin::add"),
    SUPER_ADMIN_DELETE("super_admin::delete"),
    SUPER_ADMIN_EDIT("super_admin::edit"),

    ADMIN_ADD("admin::add"),
    ADMIN_EDIT("admin::edit");


    private String value;

    PermissionsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
