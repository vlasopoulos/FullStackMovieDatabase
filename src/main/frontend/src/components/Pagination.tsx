import React from 'react'

type Props = {
    totalPages: number;
    currentPage: number;
    setPage: React.Dispatch<React.SetStateAction<string>>;
    page: string;
}

const Pagination = (props: Props) => {

    const goToPreviousPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + (props.currentPage - 1).toString());
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + (props.currentPage - 1).toString());
        };
    }

    const goToNextPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + (props.currentPage + 1).toString());
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + (props.currentPage + 1).toString());
        };
    }

    const changePage = (e: React.MouseEvent<HTMLElement>) => {
        const targetPage: string|null = e.currentTarget.textContent;
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + targetPage);
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + targetPage);
        };
        
    }

    const getPaginationGroup = () => {
        return new Array(props.totalPages).fill(null).map((_, i) => i + 1);
    };

    if (props.totalPages < 2) return (null);

    return (
        <div className='pagination'>
            {(props.currentPage==1) ? (null) : <button className='prev' onClick={goToPreviousPage}>&laquo;</button>} 
            {getPaginationGroup().map((item) => (<button className={`paginationItem ${props.currentPage === item ? 'active' : null}`} key={item}  onClick={changePage} disabled={(item===props.currentPage)}><span>{item}</span></button>))}
            {(props.currentPage==props.totalPages) ? (null) : <button className='next' onClick={goToNextPage}>&raquo;</button>} 
        </div>
    )
}

export default Pagination