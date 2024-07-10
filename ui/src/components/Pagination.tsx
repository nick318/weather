import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from './Select';
import { Button } from './Button';
import { PaginationState } from '@tanstack/react-table';
import { PaginatedResponse } from '../type';
import React, { useEffect, useState } from 'react';

interface PaginationProps<TData> {
  pagination: PaginationState;
  data: PaginatedResponse<TData> | undefined;
  onPageSizeChange: (value: number) => void;
  onPageIndexChange: React.Dispatch<React.SetStateAction<number>>;
}

export function Pagination<TData>(props: PaginationProps<TData>) {
  const [totalElements, setTotalElements] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const data = props.data;

  useEffect(() => {
    if (data) {
      setTotalElements(data.totalElements);
      setTotalPages(data.totalPages);
    }
  }, [data?.totalPages, data?.totalElements])

  const pagination = props.pagination;
  const isLast = totalPages === (pagination.pageIndex + 1)
  const isFirst = pagination.pageIndex === 0;
  return <div className="flex w-full select-none">
    <div className="flex w-full items-center text-sm text-muted-foreground">
      {isLast ? totalElements : pagination.pageSize * (pagination.pageIndex + 1)} of{' '}
      {totalElements} elements.
    </div>
    <div className="flex w-full items-center justify-center text-sm font-medium">
      Page {pagination.pageIndex + 1} of{' '}
      {totalPages}
    </div>
    <div className="flex w-full justify-end">
      <div className="flex items-center pr-2 text-sm font-medium">
        <div className="pr-2">Rows per page</div>
        <Select defaultValue={'15'} onValueChange={value => props.onPageSizeChange(Number.parseInt(value))}>
          <SelectTrigger className="w-[80px]">
            <SelectValue placeholder={pagination.pageSize}/>
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="15">15</SelectItem>
            <SelectItem value="25">25</SelectItem>
          </SelectContent>
        </Select>
      </div>
      <div className="flex items-center justify-end space-x-2">
        <Button
          variant="outline"
          size="default"
          onClick={() => props.onPageIndexChange((prevState) => prevState - 1)}
          disabled={isFirst}
        >
          Previous
        </Button>
        <Button
          variant="outline"
          size="default"
          onClick={() => props.onPageIndexChange((prevState) => prevState + 1)}
          disabled={isLast}
        >
          Next
        </Button>
      </div>
    </div>
  </div>
}