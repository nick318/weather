import { cn } from "../lib/utils"

// https://ui.shadcn.com/docs/components/skeleton
function Skeleton({
                    className,
                    ...props
                  }: React.HTMLAttributes<HTMLDivElement>) {
  return (
    <div
      className={cn("animate-pulse rounded-md bg-muted", className)}
      {...props}
    />
  )
}

export { Skeleton }
