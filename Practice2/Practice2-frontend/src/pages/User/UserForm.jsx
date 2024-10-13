import { Form, NavLink, useActionData } from "react-router-dom";
import { genders, roles } from "./User.constants.js";
import Button from "../../components/Button";
import Label from "../../components/Label";
import Input from "../../components/Input";
import Select from "../../components/Select";
import FormItem from "../../components/FormItem";
import PropTypes from "prop-types";

export default function UserForm({ user }) {
  const errors = useActionData();

  return (
    <Form action={`/user/${user.id_user}`} method="PUT" className="mt-10">
      <FormItem>
        <Label
          htmlFor="user_u"
          className="after:text-red-500 after:ml-1 after:content-['*']"
        >
          Usuario
        </Label>
        <Input
          id="user_u"
          name="user_u"
          type="text"
          defaultValue={user.user_u}
          required
        />
      </FormItem>
      <FormItem>
        <Label
          htmlFor="user_name"
          className="after:text-red-500 after:ml-1 after:content-['*']"
        >
          Nombre
        </Label>
        <Input
          id="user_name"
          name="user_name"
          type="text"
          defaultValue={user.user_name}
          required
        />
      </FormItem>
      <FormItem>
        <Label htmlFor="last_name">Apellido</Label>
        <Input
          id="last_name"
          name="last_name"
          type="text"
          defaultValue={user.last_name}
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
          defaultValue={user.email}
          required
        />
      </FormItem>
      {user.rol && (
        <FormItem>
          <Label htmlFor="rol">Rol</Label>
          <Select
            id="rol"
            name="rol"
            defaultValue={roles.includes(user.rol) ? user.rol : ""}
          >
            <option value="" disabled>
              Elige una opción
            </option>
            {roles.map((rol) => (
              <option key={rol} value={rol}>
                {rol}
              </option>
            ))}
          </Select>
        </FormItem>
      )}
      <FormItem>
        <Label htmlFor="age">Edad</Label>
        <Input id="age" name="age" type="number" defaultValue={user.age} />
      </FormItem>
      <FormItem>
        <Label htmlFor="gender">Género</Label>
        <Select
          id="gender"
          name="gender"
          defaultValue={genders.includes(user.gender) ? user.gender : ""}
        >
          <option value="" disabled>
            Elige una opción
          </option>
          {genders.map((gender) => (
            <option key={gender} value={gender}>
              {gender}
            </option>
          ))}
        </Select>
      </FormItem>
      <div className="flex gap-6 justify-end items-center mt-6">
        <NavLink to="/" className="text-sm font-semibold">
          Cancelar
        </NavLink>
        <Button name="intent" value="update">
          Guardar
        </Button>
      </div>
    </Form>
  );
}

UserForm.propTypes = {
  user: PropTypes.shape({
    id_user: PropTypes.number.isRequired,
    user_u: PropTypes.string.isRequired,
    user_name: PropTypes.string.isRequired,
    last_name: PropTypes.string,
    email: PropTypes.string.isRequired,
    rol: PropTypes.string,
    age: PropTypes.number,
    gender: PropTypes.string,
  }),
};
