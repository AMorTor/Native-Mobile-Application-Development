import { useState } from "react";
import { Form } from "react-router-dom";
import Button from "../../components/Button/index.js";
import PropTypes from "prop-types";
import * as AlertDialog from "@radix-ui/react-alert-dialog";

export default function UserDeleteDialog({ userID }) {
  const [open, setOpen] = useState(false);

  return (
    <AlertDialog.Root open={open} onOpenChange={setOpen}>
      <AlertDialog.Trigger asChild>
        <Button className="bg-red-500">Eliminar perfil</Button>
      </AlertDialog.Trigger>
      <AlertDialog.Portal>
        <AlertDialog.Overlay className="fixed inset-0 bg-black/60 data-[state=open]:animate-overlayShow" />
        <AlertDialog.Content className="fixed left-1/2 top-1/2 max-h-[85vh] w-[90vw] max-w-[500px] -translate-x-1/2 -translate-y-1/2 rounded-md bg-white p-[25px] shadow-[hsl(206_22%_7%_/_35%)_0px_10px_38px_-10px,_hsl(206_22%_7%_/_20%)_0px_10px_20px_-15px] focus:outline-none data-[state=open]:animate-contentShow">
          <Form action={`/user/${userID}`} method="DELETE">
            <AlertDialog.Title className="font-semibold">
              ¿Estás seguro de que quieres eliminar tu perfil?
            </AlertDialog.Title>
            <AlertDialog.Description className="text-sm text-gray-900 mt-2">
              Esta acción no se puede deshacer. Todos tus datos serán eliminados
              permanentemente de nuestra base de datos.
            </AlertDialog.Description>
            <AlertDialog.Cancel asChild>
              <Button
                type="button"
                className="bg-transparent text-gray-900 border mt-6 mr-2"
              >
                Cancelar
              </Button>
            </AlertDialog.Cancel>
            <Button className="bg-red-500" name="intent" value="delete">
              Si, eliminar perfil
            </Button>
          </Form>
        </AlertDialog.Content>
      </AlertDialog.Portal>
    </AlertDialog.Root>
  );
}

UserDeleteDialog.propTypes = {
  userID: PropTypes.number.isRequired,
};
