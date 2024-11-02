import PropTypes from "prop-types";
import * as RadixToast from "@radix-ui/react-toast";

export default function Toast({ open, setOpen, title, description, variant }) {
  return (
    <RadixToast.Provider swipeDirection="right">
      <RadixToast.Root
        className="flex border rounded-md gap-[12px] p-[15px] text-sm shadow-md data-[swipe=cancel]:translate-x-0 data-[swipe=move]:translate-x-[var(--radix-toast-swipe-move-x)] data-[state=closed]:animate-hide data-[state=open]:animate-slideIn data-[swipe=end]:animate-swipeOut data-[swipe=cancel]:transition-[transform_200ms_ease-out]"
        open={open}
        onOpenChange={setOpen}
      >
        <div>
          {variant === "success" && (
            <CheckIcon className="text-green-500 size-6" />
          )}
          {variant === "error" && <XIcon className="text-red-500 size-6" />}
        </div>
        <div className="flex flex-col">
          <RadixToast.Title className="mb-[5px] font-medium">
            {title}
          </RadixToast.Title>
          <RadixToast.Description>{description}</RadixToast.Description>
        </div>
      </RadixToast.Root>
      <RadixToast.Viewport className="fixed bottom-0 right-0 z-[2147483647] m-0 flex w-[390px] max-w-[100vw] list-none flex-col gap-2.5 p-[var(--viewport-padding)] outline-none [--viewport-padding:_25px]" />
    </RadixToast.Provider>
  );
}

function XIcon({ ...props }) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={1.5}
      stroke="currentColor"
      {...props}
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
      />
    </svg>
  );
}

function CheckIcon({ ...props }) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={1.5}
      stroke="currentColor"
      {...props}
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
      />
    </svg>
  );
}

Toast.propTypes = {
  open: PropTypes.bool.isRequired,
  setOpen: PropTypes.func.isRequired,
  variant: PropTypes.oneOf(["success", "error", "info"]),
  title: PropTypes.node.isRequired,
  description: PropTypes.node.isRequired,
};
