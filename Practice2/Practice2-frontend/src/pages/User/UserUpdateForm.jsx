import { Form } from "react-router-dom";
import PropTypes from "prop-types";
import UserForm from "../../components/UserForm";
import Button from "../../components/Button/index.js";

export default function UserUpdateForm({ user }) {
  return (
    <Form action={`/user/${user.id}`} method="PUT" className="mt-10">
      <UserForm user={user}>
        <Button name="intent" value="update">
          Guardar
        </Button>
      </UserForm>
    </Form>
  );
}

UserUpdateForm.propTypes = {
  user: PropTypes.shape({
    id: PropTypes.number.isRequired,
    email: PropTypes.string.isRequired,
    lastname: PropTypes.string,
    username: PropTypes.string.isRequired,
    role: PropTypes.string,
  }),
};
