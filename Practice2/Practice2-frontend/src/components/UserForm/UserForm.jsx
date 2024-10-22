import { NavLink, useActionData } from "react-router-dom";
import FormItem from "../FormItem/index.js";
import Input from "../Input/index.js";
import Select from "../Select/index.js";
import Label from "../Label/index.js";
import PropTypes from "prop-types";
import { roles } from "../../pages/User/User.constants.js";

export default function UserForm({ children, user }) {
  const error = useActionData();
  return (
    <>
      <FormItem>
        <Label
          htmlFor="username"
          className="after:text-red-500 after:ml-1 after:content-['*']"
        >
          Usuario
        </Label>
        <Input
          id="username"
          name="username"
          type="text"
          defaultValue={user?.username}
          required
        />
      </FormItem>
      <FormItem>
        <Label htmlFor="last_name">Apellido</Label>
        <Input
          id="last_name"
          name="last_name"
          type="text"
          defaultValue={user?.last_name}
        />
      </FormItem>
      <FormItem>
        <Label
          htmlFor="email"
          className="after:text-red-500 after:ml-1 after:content-['*']"
        >
          Correo
        </Label>
        <Input
          id="email"
          name="email"
          type="email"
          defaultValue={user?.email}
          required
        />
      </FormItem>
      {user?.role === "ADMIN" && (
        <FormItem>
          <Label htmlFor="role">Rol</Label>
          <Select
            id="role"
            name="role"
            defaultValue={roles.includes(user?.role) ? user?.role : ""}
            className="capitalize"
          >
            <option value="" disabled>
              Elige una opción
            </option>
            {roles.map((role) => (
              <option key={role} value={role} className="capitalize">
                {role.toLowerCase()}
              </option>
            ))}
          </Select>
        </FormItem>
      )}
      {JSON.stringify(error, null, 2)}
      <div className="flex gap-6 justify-end items-center mt-6">
        {user?.role === "ADMIN" && (
          <NavLink to="/" className="text-sm font-semibold">
            Cancelar
          </NavLink>
        )}
        {children}
      </div>
    </>
  );
}

UserForm.propTypes = {
  children: PropTypes.node.isRequired,
  user: PropTypes.shape({
    id: PropTypes.number.isRequired,
    username: PropTypes.string.isRequired,
    last_name: PropTypes.string,
    email: PropTypes.string.isRequired,
    role: PropTypes.string,
  }),
};
