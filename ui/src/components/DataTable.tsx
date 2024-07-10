import React from 'react';
import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  getPaginationRowModel,
  getSortedRowModel, SortingState,
  useReactTable
} from '@tanstack/react-table';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from './Table';
import { Skeleton } from './Skeleton';
import { Pagination } from './Pagination';
import { PaginatedResponse } from '../type';

interface DataTableProps<TData> {
  data: PaginatedResponse<TData> | undefined;
  columns: ColumnDef<TData>[]
  isLoading: boolean;
  pageSize: number;
  setPageSize: React.Dispatch<React.SetStateAction<number>>;
  pageIndex: number;
  setPageIndex: React.Dispatch<React.SetStateAction<number>>;
  sorting: SortingState;
  setSorting: React.Dispatch<React.SetStateAction<SortingState>>;
}

const skeletonPlaceholder = new Array(15);
skeletonPlaceholder.fill(1);

export function DataTable<TData>(props: DataTableProps<TData>) {
  const {
    data,
    columns,
    isLoading,
    pageSize,
    pageIndex,
    setPageSize,
    setPageIndex,
    sorting,
    setSorting
  } = props;

  const rawData = data?.content || [];
  const table = useReactTable<TData>({
    data: rawData,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    manualPagination: true,
    manualSorting: true,
    onSortingChange: setSorting,
    getSortedRowModel: getSortedRowModel(),
    state: {
      sorting,
      pagination: {
        pageSize,
        pageIndex
      },
    }
  })
  const pagination = table.getState().pagination;
  return (
    <div className="p-10">
      <div className="rounded-md border">
        <div className="h-[calc(100vh-138px)] max-h-[calc(100vh-138px)] overflow-y-scroll">
          <Table className="table-fixed">
            <TableHeader className="sticky top-0 z-10">
              {table.getHeaderGroups().map((headerGroup) => (
                <TableRow key={headerGroup.id} className="bg-gray-50">
                  {headerGroup.headers.map((header) => {
                    return (
                      <TableHead key={header.id}>
                        {header.isPlaceholder
                          ? null
                          : flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                      </TableHead>
                    )
                  })}
                </TableRow>
              ))}
            </TableHeader>
            <TableBody>
              {(isLoading) && skeletonPlaceholder.map((_) => {
                return <TableRow>
                  {table.getAllColumns().map((column) => {
                    return <TableCell className="w-[33%]" key={column.id}>
                      <Skeleton className="h-[20px] w-full"/>
                    </TableCell>
                  })}
                </TableRow>
              })}
              {table.getRowModel().rows?.map((row) => (
                <TableRow
                  key={row.id}
                  data-state={row.getIsSelected() && 'selected'}
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell className="w-[33%]" key={cell.id}>
                      {flexRender(cell.column.columnDef.cell, cell.getContext())}
                    </TableCell>
                  ))}
                </TableRow>
              ))}
              {!isLoading && rawData.length === 0 &&
                <TableRow>
                  <TableCell colSpan={columns.length} className="h-24 text-center">
                    No results.
                  </TableCell>
                </TableRow>
              }
            </TableBody>
          </Table>
        </div>
      </div>
      <div className="pt-4">
        <Pagination<TData> pagination={pagination}
                           data={data}
                           onPageSizeChange={(changedPageSize) => {
                             setPageSize(changedPageSize);
                             setPageIndex(0);
                           }}
                           onPageIndexChange={setPageIndex}/>
      </div>
    </div>
  )
}