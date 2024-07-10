import { Column, ColumnDef, SortingState } from '@tanstack/react-table'
import { WeatherData } from './type';
import { useWeather } from './queries/useWeather';
import { Button } from './components/Button';
import { ArrowUpDown } from 'lucide-react'
import { DataTable } from './components/DataTable';
import { useState } from 'react';

function headerWithSorting(column: Column<WeatherData>, name: String) {
  return <Button
    className="-ml-4"
    variant="ghost"
    onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}
  >
    {name}
    <ArrowUpDown className="ml-2 h-4 w-4"/>
  </Button>
}

export const columns: ColumnDef<WeatherData, string>[] = [
  {
    accessorKey: 'cityName',
    enableSorting: true,
    header: ({column}) => {
      return headerWithSorting(column, 'City name')
    },
  },
  {
    accessorKey: 'main',
    header: 'Description',
  },
  {
    accessorKey: 'temp',
    header: ({column}) => {
      return headerWithSorting(column, 'Temperature')
    },
    enableSorting: true,
    cell: (props) => {
      return `${Number.parseInt(props.getValue()).toFixed()}°C`;
    }
  }
];

export default function WeatherTable() {
  const [pageSize, setPageSize] = useState(15);
  const [pageIndex, setPageIndex] = useState(0);
  const [sorting, setSorting] = useState([{id: 'cityName', desc: false}] as SortingState);
  const {data, isLoading} = useWeather(
    {sortBy: sorting[0].id, sortDir: sorting[0].desc ? 'DESC' : 'ASC', pageNum: pageIndex, pageSize}
  );
  return <DataTable<WeatherData>
    data={data}
    columns={columns}
    isLoading={isLoading}
    pageSize={pageSize}
    setPageSize={setPageSize}
    pageIndex={pageIndex}
    setPageIndex={setPageIndex}
    sorting={sorting}
    setSorting={setSorting}
  />
}